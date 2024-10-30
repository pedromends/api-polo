package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.*;
import com.ifmg.apipolo.enums.Roles;
import com.ifmg.apipolo.error.UserNotFoundError;
import com.ifmg.apipolo.login.MyCustomUserDetails;
import com.ifmg.apipolo.record.LoginResponse;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.PasswordRecoveryRepository;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.ImageVO;
import com.ifmg.apipolo.vo.LoginRequest;
import com.ifmg.apipolo.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sendinblue.ApiException;

import java.util.*;

@NoArgsConstructor
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordRecoveryRepository passwordRecoveryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    BrevoEmailService brevoEmailService;

    @Autowired
    @Value("${application.security.access-token-secret")
    private String accessTokenSecret;

    @Autowired
    @Value("${application.security.refresh-token-secret")
    private String refreshTokenSecret;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ImageRepository imageRepository;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, String accessTokenSecret, String refreshTokenSecret) {
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
        this.userRepository = userRepository;
    }

    public LoginResponse registerUser(UserVO userVO) throws ApiException {

        if(!Objects.equals(userVO.getPassword(), userVO.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senhas diferentes");
        } else {
            LoginRequest loginRequest = new LoginRequest();
            LoginResponse loginResponse = new LoginResponse();
            User user = generateUser(userVO);

            loginRequest.setEmail(user.getEmail());
            loginRequest.setPassword(user.getPassword());
            String token = generateToken(loginRequest);
            loginResponse.setToken(token);
            loginResponse.setUserVO(new UserVO(user));

            return loginResponse;
        }
    }

    public User generateUser(UserVO userVO){

        try{
            User user = new User();

            user.setUsername(userVO.getEmail());
            user.setPassword(passwordEncoder.encode(userVO.getPassword()));
            user.setEmail(userVO.getEmail());
            user.setFirstName(userVO.getFirstName());
            user.setLastName(userVO.getLastName());
            user.setLocked(true);

            if(userVO.getRole().equals("ADMIN"))
                user.setRole(Roles.ADMIN.toString());

            else if(userVO.getRole().equals("CODEMASTER"))
                user.setRole(Roles.CODEMASTER.toString());

            else
                user.setRole(Roles.USER.toString());

            user.setEnabled(false);
            userRepository.save(user);

            return user;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    public String generateToken(LoginRequest loginRequest) {

        try{
            User user = userRepository.findByUsername(loginRequest.getEmail());
            var newUserTk = tokenRepository.findByUserId(user.getId());

            if(newUserTk == null) {
                MyCustomUserDetails userDetailsService = customUserDetailsService.loadByEmail(loginRequest.getEmail());

                String tokenCode = jwtTokenService.generateToken(userDetailsService);
                Token newToken = new Token(user, tokenCode);

                tokenRepository.save(newToken);
                return newToken.getToken();
            } else {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
                Authentication authentication = authenticationManager.authenticate(authToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return newUserTk.getToken();
            }
        }catch(AuthenticationException e){
            return e.getMessage();
        }
    }

    public Login refreshAccess(String refreshToken) {
        User user = userRepository.findByTokenCode(refreshToken);
        Token tokenToUpdate = tokenRepository.findByUserId(user.getId());
        Token newToken = new Token(user, 10L, refreshToken);
        Login newLogin = new Login(newToken.getToken(), refreshTokenSecret);

        tokenToUpdate.setToken(newToken.getToken());
        tokenRepository.save(tokenToUpdate);

        return newLogin;
    }

    public void forgot(String email, String originUrl) {
        var user = userRepository.findByEmail(email);

        try{
            if(user != null){
                var token = UUID.randomUUID().toString().replaceAll("-", "");
                PasswordRecovery passwordRecovery = new PasswordRecovery();
                passwordRecovery.setUser(user);
                passwordRecovery.setToken(new Token(user, token));
                passwordRecoveryRepository.save(passwordRecovery);
                mailService.sendForgotMessage(email, token, originUrl);
            }
        } catch (Exception e) {
            throw new UserNotFoundError();
        }
    }

    public UserVO getOneUser(String email) {

        User user = userRepository.findByEmail(email);
        UserVO userInfo = new UserVO();

        userInfo.setId(user.getId());
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setEmail(user.getEmail());
        userInfo.setRole(user.getRole());
        userInfo.setEducation(user.getEducation());
        userInfo.setAddress(user.getAddress());
        userInfo.setPhone(user.getPhone());
        userInfo.setAboutMe(user.getAboutMe());
        userInfo.setProfession(user.getProfession());
        userInfo.setCity(user.getCity());
        userInfo.setDepartment(user.getDepartment());

        if(user.getImg() != null){
            var image = imageRepository.findById(user.getImg().getId());
            userInfo.setImg(image.get());
        }

        return userInfo;
    }

    public void updateUser(UserVO userVO) {

        var user = userRepository.findById(userVO.getId());

        if(userVO.getImg().getId() != null){
            var image = imageRepository.findById(userVO.getImg().getId());
            image.get().setCode(userVO.getImg().getCode());
        }

        if(userVO.getFirstName() != null && userVO.getLastName() != null){
            user.get().setFirstName(userVO.getFirstName());
            user.get().setLastName(userVO.getLastName());
        }

        if(userVO.getEducation() != null)
            user.get().setEducation(userVO.getEducation());

        if(userVO.getAddress() != null)
            user.get().setAddress(userVO.getAddress());

        if(userVO.getPhone() != null)
            user.get().setPhone(userVO.getPhone());

        if(userVO.getAboutMe() != null)
            user.get().setAboutMe(userVO.getAboutMe());

        if(userVO.getProfession() != null)
            user.get().setProfession(userVO.getProfession());

        if(userVO.getImg().getCode() != null)
            user.get().setImg(userVO.getImg());

        if(userVO.getCity() != null)
            user.get().setCity(userVO.getCity());

        if(userVO.getDepartment() != null)
            user.get().setDepartment(userVO.getDepartment());

        userRepository.save(user.get());
    }

    public void changeUserPermissions(List<UserVO> userVO) {

        for (UserVO userVO1 : userVO){
            var user = userRepository.findById(userVO1.getId());
            user.get().setRole(userVO1.getRole());
            userRepository.save(user.get());
        }
    }

    public List<UserVO> listUser(){

        List<UserVO> listVO = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for(User user : list)
            listVO.add(new UserVO(user));

        return listVO;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserFromToken(String substring) {
        return userRepository.findByTokenCode(substring);
    }
}

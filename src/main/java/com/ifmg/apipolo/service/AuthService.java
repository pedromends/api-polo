package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Login;
import com.ifmg.apipolo.entity.PasswordRecovery;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.enums.Roles;
import com.ifmg.apipolo.error.UserNotFoundError;
import com.ifmg.apipolo.repository.ImageRepository;
import com.ifmg.apipolo.repository.PasswordRecoveryRepository;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.UserVO;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    @Value("${application.security.access-token-secret")
    private String accessTokenSecret;

    @Autowired
    @Value("${application.security.refresh-token-secret")
    private String refreshTokenSecret;

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

    public UserVO registerUser(UserVO userVO){

        if(!Objects.equals(userVO.getPassword(), userVO.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senhas diferentes");
        } else {
            try{
                User user = new User();
                UserVO userReturn = new UserVO();

                user.setUsername(userVO.getUsername());
                user.setPassword(passwordEncoder.encode(userVO.getPassword()));
                user.setEmail(userVO.getEmail());
                user.setFirstName(userVO.getFirstName());
                user.setLastName(userVO.getLastName());
                user.setLocked(true);

                if(userVO.getRole().equals("ADMIN"))
                    user.setRole(Roles.ADMIN.toString());

                else if(userVO.getRole().equals("CODEMASTER"))
                    user.setRole(Roles.CODEMASTER.toString());

                user.setEnabled(false);
                userRepository.save(user);

                User originalUser =  userRepository.findByEmail(userVO.getEmail());

                userReturn.setEmail(originalUser.getEmail());
                userReturn.setUsername(originalUser.getUsername());
                userReturn.setFirstName(originalUser.getFirstName());
                userReturn.setLastName(originalUser.getFirstName());

                return userReturn;
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
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
        userInfo.setEducation(user.getEducation());
        userInfo.setAddress(user.getAddress());
        userInfo.setPhone(user.getPhone());
        userInfo.setAboutMe(user.getAboutMe());
        userInfo.setProfession(user.getProfession());
        userInfo.setImg(user.getImg());
        userInfo.setCity(user.getCity());
        userInfo.setDepartment(user.getDepartment());

        return userInfo;
    }

    public void updateUser(UserVO userVO) {

        var user = userRepository.findById(userVO.getId());

        if(userVO.getImg().getId() != null){
            var image = imageRepository.findById(userVO.getId());
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

        if(userVO.getImg() != null)
            user.get().setImg(userVO.getImg());

        if(userVO.getCity() != null)
            user.get().setCity(userVO.getCity());

        if(userVO.getDepartment() != null)
            user.get().setDepartment(userVO.getDepartment());

        userRepository.save(user.get());
    }

//    public void changeUserPermissions(UserVO userVO, String permission) {
//
//        var user = userRepository.findById(userVO.getId());
//
//        if(user.isPresent())
//            user.get().setRole(permission);
//
//        userRepository.save(user.get());
//    }

    public List<UserVO> listUser(){

        List<UserVO> listVO = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for(User user : list)
            listVO.add(new UserVO(user));

        return listVO;
    }


    public void deleteTalentCard(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserFromToken(String substring) {
        return userRepository.findByTokenCode(substring);
    }
}

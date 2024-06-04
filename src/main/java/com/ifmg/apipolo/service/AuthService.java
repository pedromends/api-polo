package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Login;
import com.ifmg.apipolo.entity.PasswordRecovery;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.error.UserNotFoundError;
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

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, String accessTokenSecret, String refreshTokenSecret) {
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
        this.userRepository = userRepository;
    }

    public String registerUser(UserVO userVO){

        if(!Objects.equals(userVO.getPassword(), userVO.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senhas diferentes");
        } else {
            try{
                User user = new User();

                user.setUsername(userVO.getUsername());
                user.setPassword(passwordEncoder.encode(userVO.getPassword()));
                user.setEmail(userVO.getEmail());
                user.setFirstName(userVO.getFirstName());
                user.setLastName(userVO.getLastName());
                user.setLocked(true);
                user.setRole(userVO.getRole());
                user.setEnabled(false);
                userRepository.save(user);
                User savedUser = userRepository.findByEmail(userVO.getEmail());
                Token newToken = new Token(savedUser, 10L, accessTokenSecret);

                tokenRepository.save(newToken);
                return newToken.getToken();
            }catch (Exception e){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
            }
        }
    }

    public Login login(String email, String password){
        var user = userRepository.findByEmail(email);
                // .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas"));

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");

        Token newToken = new Token(user, 10L, accessTokenSecret);
        Token loggedUser = tokenRepository.findByUserId(newToken.getUser().getId());

        tokenRepository.save(newToken);

        return new Login(loggedUser.getUser(), newToken.getToken(), refreshTokenSecret);
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

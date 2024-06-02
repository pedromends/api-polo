package com.ifmg.apipolo.service;

import com.ifmg.apipolo.controller.AuthController;
import com.ifmg.apipolo.entity.Login;
import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
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

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Value("${application.security.access-token-secret}")
    private String accessTokenSecret;

    @Autowired
    @Value("${application.security.refresh-token-secret}")
    private String refreshTokenSecret;

    public AuthService() {

    }

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, String accessTokenSecret, String refreshTokenSecret) {
        this.passwordEncoder = passwordEncoder;
        this.accessTokenSecret = accessTokenSecret;
        this.refreshTokenSecret = refreshTokenSecret;
        this.userRepository = userRepository;
    }

    public Optional<User> registerUser(UserVO userVO){

        if(!Objects.equals(userVO.getPassword(), userVO.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senhas diferentes");
        } else {

            //colocar try catch depois
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

            return userRepository.findByEmail(user.getEmail());
        }
    }

    public Login login(String email, String password){
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas"));

        if(!passwordEncoder.matches(password, user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");

        Token newToken = new Token(user, 10L, accessTokenSecret);

        tokenRepository.save(newToken);
        Token loggedUser = tokenRepository.findByUserId(newToken.getUser().getId());
        System.out.println(loggedUser);
        return new Login(loggedUser.getUser(),
                accessTokenSecret, refreshTokenSecret);

    }

    public List<UserVO> listUser(){

        List<UserVO> listVO = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for(User user : list)
            listVO.add(new UserVO(user));

        return listVO;
    }

    public void updateUser(UserVO userVO){

        User user = userRepository.getReferenceById(userVO.getId());

        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setEmail(userVO.getEmail());
        user.setFirstName(userVO.getFirstName());
        user.setLastName(userVO.getLastName());
        user.setRole(userVO.getRole());

        userRepository.save(user);
    }

    public void deleteTalentCard(Long id) {
        userRepository.deleteById(id);
    }
}

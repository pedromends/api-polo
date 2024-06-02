package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.UserVO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserVO userVO){

        if(!Objects.equals(userVO.getPassword(), userVO.getConfirmPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senhas diferentes");
        } else {
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
        }
    }

    public Token login(UserVO userVO){
        var user = userRepository.findByEmail(userVO.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas"));

        if(!passwordEncoder.matches(userVO.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credenciais inválidas");

        var issueDate = Instant.now();

        Token token = new Token(user, Jwts.builder()
                .claim("id_usuario", user.getId())
                .setIssuedAt(Date.from(issueDate))
                .setExpiration(Date.from(issueDate.plus(10L, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString("very_long_and_secure_safe_access_key".getBytes()))
                .compact());

        return token;
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

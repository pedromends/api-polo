package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

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

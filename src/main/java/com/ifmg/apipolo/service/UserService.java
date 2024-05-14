package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.Token;
import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.repository.TokenRepository;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.TokenVO;
import com.ifmg.apipolo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserVO userVO){
        User user = new User();

        user.setUsername(userVO.getUsername());
        user.setPassword(userVO.getPassword());
        user.setEmail(userVO.getEmail());
        user.setFirstName(userVO.getFirstName());
        user.setLastName(userVO.getLastName());
        user.setLocked(userVO.getLocked());
        user.setRole(userVO.getRole());
        user.setEnabled(userVO.getEnabled());

        userRepository.save(user);
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
        user.setLocked(userVO.getLocked());
        user.setRole(userVO.getRole());
        user.setEnabled(userVO.getEnabled());

        userRepository.save(user);
    }

    public void deleteTalentCard(Long id) {
        userRepository.deleteById(id);
    }
}

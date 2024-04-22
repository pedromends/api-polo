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

    public List<UserVO> list(){

        List<UserVO> listVO = new ArrayList<>();
        List<User> list = userRepository.findAll();

        for(User user : list)
            listVO.add(new UserVO(user));

        return listVO;
    }
}

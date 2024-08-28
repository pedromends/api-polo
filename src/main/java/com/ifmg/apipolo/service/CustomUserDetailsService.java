package com.ifmg.apipolo.service;

import com.ifmg.apipolo.entity.User;
import com.ifmg.apipolo.login.MyCustomUserDetails;
import com.ifmg.apipolo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public MyCustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("Unable To Load User");

        return new MyCustomUserDetails(user);
    }

    public MyCustomUserDetails loadByEmail(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new UsernameNotFoundException("Unable To Load User");
        } else {
            return new MyCustomUserDetails(user);
        }
    }
}

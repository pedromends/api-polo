package com.ifmg.apipolo.record;

import com.ifmg.apipolo.vo.UserVO;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginResponse {

    public UserVO userVO;
    public String token;

    public LoginResponse (String token){
        this.token = token;
    }
}

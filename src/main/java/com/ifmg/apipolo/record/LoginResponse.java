package com.ifmg.apipolo.record;

import com.ifmg.apipolo.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    public UserVO userVO;
    public String token;

    public LoginResponse (String token){
        this.token = token;
    }

    public LoginResponse() {

    }

    public LoginResponse (UserVO userVO){
        this.userVO = userVO;
    }
}

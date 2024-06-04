package com.ifmg.apipolo.vo;

import com.ifmg.apipolo.login.MyCustomUserDetails;
import lombok.Getter;

@Getter
public class LoginResponse {

    private final String token;
    private MyCustomUserDetails myCustomUserDetails;

    public LoginResponse(String token, MyCustomUserDetails myCustomUserDetails) {
        this.token = token;
        this.myCustomUserDetails = myCustomUserDetails;
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public Long getUserId(){
        return this.myCustomUserDetails.getUserId();
    }

    public String getUsername(){
        return this.myCustomUserDetails.getUsername();
    }

    public String getFirstName(){
        return this.myCustomUserDetails.getFirstName();
    }

    public String getLastName(){
        return this.myCustomUserDetails.getLastName();
    }
}

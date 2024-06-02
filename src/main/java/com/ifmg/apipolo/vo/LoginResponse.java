package com.ifmg.apipolo.vo;

import lombok.Getter;

@Getter
public class LoginResponse {
    String token;

    public LoginResponse(String token) {
        this.token = token;
    }
}

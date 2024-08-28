package com.ifmg.apipolo.vo;

import lombok.Getter;

@Getter
public class LoginRequest {

    private final String email;
    private final String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}

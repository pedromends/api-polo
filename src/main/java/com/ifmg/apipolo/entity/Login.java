package com.ifmg.apipolo.entity;

import lombok.*;

@Getter
@Setter
@ToString
public class Login {

    private Token accessToken;
    private Token refreshToken;
    private String currentAccess;
    private String oldAccess;
    private static final Long ACCESS_TOKEN_VALIDITY = 1L;
    private static final Long REFRESH_TOKEN_VALIDITY = 1L;

    public Login(User user, String accessSecret, String refreshSecret) {
        this.accessToken = new Token(user, accessSecret);
        this.refreshToken = new Token(user, refreshSecret);
    }

    public Login(String currentAccess, String oldAccess) {
        this.currentAccess = currentAccess;
        this.oldAccess = oldAccess;
    }

    public Login(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}


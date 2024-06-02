package com.ifmg.apipolo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Login {

    private final Token accessToken;
    private final Token refreshToken;

    public Login(Token accessToken, Token refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Login(User user, String accessSecret, String refreshSecret) {
        this.accessToken = new Token(user, 10L, accessSecret);
        this.refreshToken = new Token(user, 1440L, refreshSecret);
    }
}

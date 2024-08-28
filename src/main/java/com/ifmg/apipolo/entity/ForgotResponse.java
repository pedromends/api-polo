package com.ifmg.apipolo.entity;

import lombok.Getter;

@Getter
public class ForgotResponse {

    private final String message;

    public ForgotResponse(String message) {
        this.message = message;
    }
}

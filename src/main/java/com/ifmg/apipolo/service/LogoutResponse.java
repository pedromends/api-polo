package com.ifmg.apipolo.service;

import lombok.Getter;

@Getter
public class LogoutResponse {
    private String message;

    public LogoutResponse(String message) {
        this.message = message;
    }
}

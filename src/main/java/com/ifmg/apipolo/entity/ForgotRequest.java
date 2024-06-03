package com.ifmg.apipolo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class ForgotRequest {

    private String email;

    public ForgotRequest(String email) {
        this.email = email;
    }
}

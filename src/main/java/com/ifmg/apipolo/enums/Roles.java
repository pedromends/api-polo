package com.ifmg.apipolo.enums;

import lombok.Getter;

@Getter
public enum Roles {
    CODEMASTER("CODEMASTER"),
    ADMIN("ADMIN"),
    RESEARCHER("researcher"),
    STUDENT("student"),
    USER("user");

    private String role;

    Roles(String role) {
    }
}

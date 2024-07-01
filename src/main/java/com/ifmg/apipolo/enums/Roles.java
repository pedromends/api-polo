package com.ifmg.apipolo.enums;

import lombok.Getter;

@Getter
public enum Roles {
    CODEMASTER("CODEMASTER"),
    ADMIN("ADMIN"),
    RESEARCHER("researcher"),
    STUDENT("student"),
    CLIENT("client");

    private String role;

    Roles(String role) {
    }
}

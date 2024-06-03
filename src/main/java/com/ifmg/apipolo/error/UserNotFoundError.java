package com.ifmg.apipolo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundError extends ResponseStatusException {

    public UserNotFoundError() {
        super(HttpStatus.UNAUTHORIZED, "Usuário não encontrado");
    }
}

package com.ifmg.apipolo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")

public class TestController {

    @PreAuthorize("hasAuthority('CODEMASTER')")
    @GetMapping("/codemaster")
    public ResponseEntity<String> unlockCodemaster()  {
        return new ResponseEntity<>("Acesso Nível Codemaster concedido!", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> unlockAdmin()  {
        return new ResponseEntity<>("Acesso Nível Administrador concedido!", HttpStatus.OK);
    }
}

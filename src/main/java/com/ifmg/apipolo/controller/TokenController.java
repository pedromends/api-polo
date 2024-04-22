package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(tokenService.list(), HttpStatus.OK);
    }
}

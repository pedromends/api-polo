package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.PresenterCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/presenter-card")
public class PresenterCardController {

    @Autowired
    PresenterCardService presenterCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(presenterCardService.list(), HttpStatus.OK);
    }
}

package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.PresentationCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/presenter-card")
public class PresentationCardController {

    @Autowired
    PresentationCardService presentationCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(presentationCardService.list(), HttpStatus.OK);
    }
}

package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.AdvantagesCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/advantages")
public class AdvantagesCardController {

    @Autowired
    AdvantagesCardService advantagesCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(advantagesCardService.list(), HttpStatus.OK);
    }
}

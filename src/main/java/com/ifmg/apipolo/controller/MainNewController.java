package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.MainNewService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/main-new", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainNewController {

    @Autowired
    MainNewService mainNewService;

    @GetMapping("/list")
    public ResponseEntity<Object> listMainNews()  {
        return new ResponseEntity<>(mainNewService.list(), HttpStatus.OK);
    }
}

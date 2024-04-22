package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.MainNewService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/main-new")
public class MainNewController {

    @Autowired
    MainNewService mainNewService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(mainNewService.list(), HttpStatus.OK);
    }
}

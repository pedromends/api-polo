package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.EventCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/event")
public class EventCardController {


    @Autowired
    EventCardService eventCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listEvents()  {
        return new ResponseEntity<>(eventCardService.list(), HttpStatus.OK);
    }
}

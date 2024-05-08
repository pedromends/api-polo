package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CapacitationCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/capacitation")
public class CapacitationCardController {

    @Autowired
    CapacitationCardService capacitationCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(capacitationCardService.list(), HttpStatus.OK);
    }
}

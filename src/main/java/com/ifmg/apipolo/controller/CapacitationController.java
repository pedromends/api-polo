package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CapacitationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/capacitation")
public class CapacitationController {

    @Autowired
    CapacitationService capacitationService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(capacitationService.list(), HttpStatus.OK);
    }
}

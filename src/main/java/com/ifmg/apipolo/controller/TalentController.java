package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.TalentCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/talent")
public class TalentController {

    @Autowired
    TalentCardService talentCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(talentCardService.list(), HttpStatus.OK);
    }
}

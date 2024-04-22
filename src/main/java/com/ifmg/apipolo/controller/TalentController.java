package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.AdvantagesService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/talent")
public class TalentController {

    @Autowired
    TalentService talentService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(talentService.list(), HttpStatus.OK);
    }
}

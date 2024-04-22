package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.AdvantagesService;
import com.ifmg.apipolo.service.ResearcherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/researcher")
public class ResearcherController {

    @Autowired
    ResearcherService researcherService;

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(researcherService.list(), HttpStatus.OK);
    }
}

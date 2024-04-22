package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/company")
public class CompanyController {


    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(companyService.list(), HttpStatus.OK);
    }
}

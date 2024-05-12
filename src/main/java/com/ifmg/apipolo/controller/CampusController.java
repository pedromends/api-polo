package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    CampusService campusService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCampus()  {
        return new ResponseEntity<>(campusService.listCampus(), HttpStatus.OK);
    }
}

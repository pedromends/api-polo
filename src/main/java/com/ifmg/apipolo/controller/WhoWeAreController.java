package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.WhoWeAreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/who-we-are")
public class WhoWeAreController {

    @Autowired
    WhoWeAreService whoWeAreService;

    @GetMapping("/get")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(whoWeAreService.getText(), HttpStatus.OK);
    }
}

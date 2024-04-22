package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.NewsCardService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/news-card")
public class NewsCardController {

    @Autowired
    NewsCardService newsCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(newsCardService.list(), HttpStatus.OK);
    }
}

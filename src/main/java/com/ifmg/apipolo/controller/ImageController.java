package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/list")
    public ResponseEntity<Object> listImages()  {
        return new ResponseEntity<>(imageService.list(), HttpStatus.OK);
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id") Long id)  {
        return new ResponseEntity<>(imageService.getOne(id), HttpStatus.OK);
    }
}

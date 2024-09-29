package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ImageService;
import com.ifmg.apipolo.vo.ImageVO;
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

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody ImageVO imageVO) {
        imageService.createImage(imageVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/news")
    public ResponseEntity<ImageVO> newImage(@RequestBody ImageVO imageVO) {
        return new ResponseEntity<>(imageService.newImage(imageVO), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listImages()  {
        return new ResponseEntity<>(imageService.list(), HttpStatus.OK);
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id") Long id)  {
        return new ResponseEntity<>(imageService.getOne(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateImage(@RequestBody ImageVO imageVO) {
        imageService.updateImage(imageVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteImage(@RequestParam("id") Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

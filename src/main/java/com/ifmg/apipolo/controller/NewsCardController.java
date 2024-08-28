package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.NewsCardService;
import com.ifmg.apipolo.vo.NewsCardVO;
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

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody NewsCardVO newsCardVO) {
        newsCardService.updateCard(newsCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listNewsCards()  {
        return new ResponseEntity<>(newsCardService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody NewsCardVO newsCardVO) {
        newsCardService.updateCard(newsCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteNewsCard(@RequestParam("id")Long id)  {
        newsCardService.deleteModality(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

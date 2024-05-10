package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.AdvantagesCardService;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import com.ifmg.apipolo.vo.PresentationCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/advantages")
public class AdvantagesCardController {

    @Autowired
    AdvantagesCardService advantagesCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody AdvantagesCardVO advantagesCardVO) {
        advantagesCardService.createCard(advantagesCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(advantagesCardService.list(), HttpStatus.OK);
    }
}

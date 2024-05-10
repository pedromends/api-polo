package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.MainNewCardService;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/main-new", produces = MediaType.APPLICATION_JSON_VALUE)
public class MainNewCardController {

    @Autowired
    MainNewCardService mainNewCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody MainNewCardVO mainNewCardVO) {
        mainNewCardService.createMainNew(mainNewCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listMainNews()  {
        return new ResponseEntity<>(mainNewCardService.list(), HttpStatus.OK);
    }
}

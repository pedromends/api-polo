package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.MainNewCardService;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/main-new")
public class MainNewCardController {

    // Apenas atualização, não criar método de deleção como modo de proteção

    @Autowired
    MainNewCardService mainNewCardService;

    @GetMapping("/list")
    public ResponseEntity<Object> listMainNews()  {
        return new ResponseEntity<>(mainNewCardService.list(), HttpStatus.OK);
    }
}

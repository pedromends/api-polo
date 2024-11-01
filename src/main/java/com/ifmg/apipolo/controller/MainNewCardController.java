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

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody MainNewCardVO mainNewCardVO) {
        mainNewCardService.createMainNew(mainNewCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listMainNews()  {
        return new ResponseEntity<>(mainNewCardService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateMainNew(@RequestBody MainNewCardVO mainNewCardVO)  {
        mainNewCardService.updateMainNew(mainNewCardVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<Object> deleteMainNew(@RequestParam("id") Long id)  {
        mainNewCardService.deleteMainNewService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

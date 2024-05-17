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

    // Mínimo 3 cards e imagem da capa, cada card tem título, subtítulo e imagem

    @Autowired
    AdvantagesCardService advantagesCardService;
//
//    @PutMapping("/create")
//    public ResponseEntity<Object> createCard(@RequestBody AdvantagesCardVO advantagesCardVO) {
//        advantagesCardService.createCard(advantagesCardVO);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody AdvantagesCardVO advantagesCardVO) {
        advantagesCardService.updateCard(advantagesCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCards()  {
        return new ResponseEntity<>(advantagesCardService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCard(@RequestParam("id") Long id)  {
        advantagesCardService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

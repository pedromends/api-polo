package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.PresentationCardService;
import com.ifmg.apipolo.vo.PositionVO;
import com.ifmg.apipolo.vo.PresentationCardVO;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequestMapping("/presenter-card")
public class PresentationCardController {

    // Mínimo 4 cards, apenas atualização

    @Autowired
    PresentationCardService presentationCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody PresentationCardVO presentationCardVO) {
        presentationCardService.createCard(presentationCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(presentationCardService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updatePresentationCard(@RequestBody PresentationCardVO presentationCardVO)  {
        presentationCardService.updatePresentationCard(presentationCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deletePresentationCard(@PathVariable("id")Long id)  {
        presentationCardService.deletePresentationCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

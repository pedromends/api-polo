package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CapacitationCardService;
import com.ifmg.apipolo.vo.CapacitationCardVO;
import com.ifmg.apipolo.vo.MainNewCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/capacitation")
public class CapacitationCardController {

    @Autowired
    CapacitationCardService capacitationCardService;

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody CapacitationCardVO capacitationCardVO) {
        capacitationCardService.updateMainNew(capacitationCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCapacitations()  {
        return new ResponseEntity<>(capacitationCardService.list(), HttpStatus.OK);
    }

}

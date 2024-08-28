package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.TalentCardService;
import com.ifmg.apipolo.vo.TalentCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/talent")
public class TalentCardController {

    @Autowired
    TalentCardService talentCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody TalentCardVO talentCardVO) {
        talentCardService.createMainNew(talentCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(talentCardService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateAdvantages(@RequestBody TalentCardVO talentCardVO)  {
        talentCardService.updateTalentCard(talentCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteAdvantages(@PathVariable("id") Long id)  {
        talentCardService.deleteTalentCard(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

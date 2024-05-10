package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.entity.EventCard;
import com.ifmg.apipolo.service.EventCardService;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import com.ifmg.apipolo.vo.EventCardVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/event")
public class EventCardController {


    @Autowired
    EventCardService eventCardService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody EventCardVO eventCardVO) {
        eventCardService.createEvent(eventCardVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listEvents()  {
        return new ResponseEntity<>(eventCardService.list(), HttpStatus.OK);
    }
}

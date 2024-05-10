package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.WhoWeAreService;
import com.ifmg.apipolo.vo.WhoWeAreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/who-we-are")
public class WhoWeAreController {

    @Autowired
    WhoWeAreService whoWeAreService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody WhoWeAreVO whoWeAreVO) {
        whoWeAreService.createAboutUs(whoWeAreVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(whoWeAreService.getText(), HttpStatus.OK);
    }
}

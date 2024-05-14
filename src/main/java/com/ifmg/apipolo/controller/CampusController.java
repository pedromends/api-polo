package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CampusService;
import com.ifmg.apipolo.vo.AdvantagesCardVO;
import com.ifmg.apipolo.vo.CampusVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/campus")
public class CampusController {

    @Autowired
    CampusService campusService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody CampusVO campusVO) {
        campusService.createCampus(campusVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody CampusVO campusVO) {
        campusService.updateCampus(campusVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCampus()  {
        return new ResponseEntity<>(campusService.listCampus(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCard(@RequestParam("id") Long id)  {
        campusService.deleteCampus(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

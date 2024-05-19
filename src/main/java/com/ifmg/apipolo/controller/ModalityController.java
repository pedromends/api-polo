package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ModalityService;
import com.ifmg.apipolo.vo.ModalityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/modality")
public class ModalityController {

    @Autowired
    ModalityService modalityService;

    @PostMapping("/create")
    public ResponseEntity<Object> createModality(@RequestBody ModalityVO modalityVO)  {
        modalityService.createModality(modalityVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listModality()  {
        return new ResponseEntity<>(modalityService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateModality(@RequestBody ModalityVO modalityVO)  {
        modalityService.updateModality(modalityVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteModality(@RequestParam("id") Long id)  {
        modalityService.deleteModality(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

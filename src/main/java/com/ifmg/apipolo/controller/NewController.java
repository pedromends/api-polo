package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.NewService;
import com.ifmg.apipolo.vo.NewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/new")
public class NewController {

    @Autowired
    NewService newService;

    @PostMapping("/create")
    public ResponseEntity<Object> createNew(@RequestBody NewVO newVO)  {
        newService.createNew(newVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listNew()  {
        return new ResponseEntity<>(newService.list(), HttpStatus.OK);
    }

    @GetMapping("/show-one/{id}")
    public ResponseEntity<Object> getOne(@PathVariable("id") Long id)  {
        return new ResponseEntity<>(newService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/get-three")
    public ResponseEntity<Object> listThree()  {
        return new ResponseEntity<>(newService.listThree(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateNew(@RequestBody NewVO newVO)  {
        newService.updateNew(newVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteNew(@RequestParam("id") Long id)  {
        newService.deleteNew(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

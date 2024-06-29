package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.EdictsService;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/edicts")
public class EdictsController {

    @Autowired
    EdictsService edictsService;

    @PostMapping("/create")
    public ResponseEntity<Object> createEdicts(@RequestBody EdictsVO EdictsVO) {
        edictsService.createEdict(EdictsVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateEdicts(@RequestBody EdictsVO EdictsVO) {
        edictsService.updateEdicts(EdictsVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listEdicts()  {
        return new ResponseEntity<>(edictsService.listEdicts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEdicts(@RequestParam("id") Long id)  {
        edictsService.deleteEdicts(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

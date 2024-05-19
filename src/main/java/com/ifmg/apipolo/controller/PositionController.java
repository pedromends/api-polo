package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.PositionService;
import com.ifmg.apipolo.vo.PositionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/position", produces = MediaType.APPLICATION_JSON_VALUE)
public class PositionController {

    @Autowired
    PositionService positionService;

    @PostMapping("/create")
    public ResponseEntity<Object> createPosition(@RequestBody PositionVO positionVO) {
        positionService.createPosition(positionVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(positionService.listPositions(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updatePosition(@RequestBody PositionVO positionVO)  {
        positionService.updatePosition(positionVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePosition(@RequestParam("id")Long id)  {
        positionService.deletePosition(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

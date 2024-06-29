package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.DocsService;
import com.ifmg.apipolo.service.EdictsService;
import com.ifmg.apipolo.vo.DocsVO;
import com.ifmg.apipolo.vo.EdictsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/docs")
public class DocsController {

    @Autowired
    DocsService docsService;

    @PostMapping("/create")
    public ResponseEntity<Object> createDocs(@RequestBody DocsVO docsVO) {
        docsService.createDocs(docsVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateDocs(@RequestBody DocsVO docsVO) {
        docsService.updateDocs(docsVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listDocs()  {
        return new ResponseEntity<>(docsService.listDocs(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteEdicts(@RequestParam("id") Long id)  {
        docsService.deleteDocs(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

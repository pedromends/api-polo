package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ResearcherService;
import com.ifmg.apipolo.vo.ResearcherVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/researcher")
public class ResearcherController {

    @Autowired
    ResearcherService researcherService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody ResearcherVO researcherVO) {
        researcherService.createResearcher(researcherVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody ResearcherVO researcherVO) {
        researcherService.updateResearcher(researcherVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(researcherService.list(), HttpStatus.OK);
    }

    @DeleteMapping("delete")
    public void deleteProject(Long id){
        researcherService.deleteResearcher(id);
    }
}

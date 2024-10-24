package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.ResearcherService;
import com.ifmg.apipolo.vo.NewVO;
import com.ifmg.apipolo.vo.ResearcherVO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/search")
    public ResponseEntity<List<ResearcherVO>> searchItems(@RequestParam String query) {
        return new ResponseEntity<>(researcherService.searchItems(query), HttpStatus.OK);
    }

    @GetMapping("/get-by-email")
    public ResponseEntity<Object> getByEmail(@RequestParam("email") String email)  {
        return new ResponseEntity<>(researcherService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/get-one/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") Long id)  {
        return new ResponseEntity<>(researcherService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listAdvantages()  {
        return new ResponseEntity<>(researcherService.list(), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("delete/{id}")
    public void deleteProject(@PathVariable("id") Long id){
        researcherService.deleteResearcher(id);
    }
}

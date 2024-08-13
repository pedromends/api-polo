package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.FAQService;
import com.ifmg.apipolo.vo.FAQVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/FAQ")
public class FAQController {

    @Autowired
    FAQService faqService;

    @PostMapping("/create")
    public ResponseEntity<Object> createFaq(@RequestBody FAQVO faqVO) {
        faqService.createFAQ(faqVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateFaq(@RequestBody FAQVO faqVO) {
        faqService.updateFAQ(faqVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listFaq()  {
        return new ResponseEntity<>(faqService.listFAQ(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteFaq(@PathVariable("id") Long id)  {
        faqService.deleteFAQ(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

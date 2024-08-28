package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.service.CompanyService;
import com.ifmg.apipolo.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCompany(@RequestBody CompanyVO companyVO) {
        companyService.createCompany(companyVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCompanies()  {
        return new ResponseEntity<>(companyService.list(), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCompany(@RequestBody CompanyVO companyVO) {
        companyService.updateCompany(companyVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

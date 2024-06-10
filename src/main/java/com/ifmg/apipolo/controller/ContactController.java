package com.ifmg.apipolo.controller;


import com.ifmg.apipolo.service.ContactService;
import com.ifmg.apipolo.vo.ContactVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/create")
    public ResponseEntity<Object> createCard(@RequestBody ContactVO contactVO) {
        contactService.createContact(contactVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

package com.ifmg.apipolo.controller;


import com.ifmg.apipolo.service.ContactService;
import com.ifmg.apipolo.vo.ContactVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public ResponseEntity<List<ContactVO>> getContacts() {
        contactService.listContacts();
        return new ResponseEntity<>(contactService.listContacts() ,HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ContactVO>> findAll() {
        return new ResponseEntity<>(contactService.getAll() ,HttpStatus.OK);
    }

    @PutMapping("/read-notification")
    public ResponseEntity<Object> readNotification(@RequestBody String id) {
        contactService.readNotification(Long.parseLong(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

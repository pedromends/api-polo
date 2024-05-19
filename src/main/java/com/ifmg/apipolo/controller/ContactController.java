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

    @PutMapping("/update")
    public ResponseEntity<Object> updateCard(@RequestBody ContactVO contactVO) {
        contactService.updateCampus(contactVO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Object> listCampus()  {
        return new ResponseEntity<>(contactService.listContacts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCard(@RequestParam("id") Long id)  {
        contactService.deleteCampus(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}

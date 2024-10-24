package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.record.EmailMessage;
import com.ifmg.apipolo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    EmailService emailSenderService;

    @PostMapping("/send-register")
    public ResponseEntity<Object> sendEmail(@RequestBody EmailMessage emailMessage) {
        emailSenderService.sendRegisterEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
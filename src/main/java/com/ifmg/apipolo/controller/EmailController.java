package com.ifmg.apipolo.controller;

import com.ifmg.apipolo.record.EmailMessage;
import com.ifmg.apipolo.service.BrevoEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sendinblue.ApiException;

@CrossOrigin("*")
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    BrevoEmailService brevoEmailService;

    @PostMapping("/send-register")
    public ResponseEntity<String> sendEmail(@RequestBody EmailMessage email) throws ApiException {
        return new ResponseEntity<>(brevoEmailService.sendEmail(email), HttpStatus.OK);
    }
}
package com.ifmg.apipolo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendRegisterEmail(String to, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setFrom("joaopedromend14@gmail.com");
        message.setSubject(subject);
        message.setText("Confirma essa merda");

        mailSender.send(message);
    }

    public void sendRecoveryEmail(String to, String subject, String body){

    }
}

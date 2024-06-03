package com.ifmg.apipolo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final String defaultFrontEndUrl;

    public MailService(JavaMailSender mailSender, @Value("${application.frontend-default-url}")String defaultFrontEndUrl) {
        this.mailSender = mailSender;
        this.defaultFrontEndUrl = defaultFrontEndUrl;
    }

    public void sendForgotMessage(String email, String token, String baseUrl) {

        var url = baseUrl != null ? baseUrl : defaultFrontEndUrl;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("joaopedromend14@gmail.com");
        message.setTo(email);
        message.setSubject("Recuperação de senha");
        message.setText(String.format("Click <a href=\" %s/reset/%s \">here<a/> to reser your password",url,token));

        mailSender.send(message);
    }
}

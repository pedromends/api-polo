package com.ifmg.apipolo.service;

import com.ifmg.apipolo.record.EmailMessage;
import com.ifmg.apipolo.repository.UserRepository;
import com.ifmg.apipolo.vo.LoginRequest;
import com.ifmg.apipolo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import sendinblue.ApiClient;
import sendinblue.ApiException;
import sendinblue.Configuration;
import sendinblue.auth.ApiKeyAuth;
import sibApi.TransactionalEmailsApi;
import sibModel.*;

import java.util.*;

@Service
public class BrevoEmailService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TemplateEngine templateEngine;

    private static final String API_KEY = "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";

    public String sendEmail(EmailMessage email) throws ApiException {
        UserVO userVO = new UserVO(userRepository.findByEmail(email.getEmail()));
        SendSmtpEmailReplyTo sendSmtpEmailReplyTo = new SendSmtpEmailReplyTo();
        TransactionalEmailsApi api = new TransactionalEmailsApi();
        SendSmtpEmailSender sender = new SendSmtpEmailSender();
        List<SendSmtpEmailTo> toList = new ArrayList<>();
        SendSmtpEmailTo to = new SendSmtpEmailTo();
        SendSmtpEmail sendSmtpEmail = new SendSmtpEmail();
        Properties headers = new Properties();
        LoginRequest loginRequest = new LoginRequest(userVO.getEmail(), userVO.getPassword());

        ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiKeyAuth apiKey = (ApiKeyAuth) defaultClient.getAuthentication("api-key");

        apiKey.setApiKey(API_KEY);sender.setEmail("joaopedromend14@gmail.com");

        sendSmtpEmailReplyTo.setEmail(email.getEmail());
        sendSmtpEmailReplyTo.setName("Secretaria do Polo de Inovação IFMG");

        sender.setName("Polo de Inovação IFMG");

        to.setEmail(email.getEmail());
        to.setName(userVO.getFirstName() + " " + userVO.getLastName());

        toList.add(to);

        headers.setProperty("Content-Type", "application/json");
        headers.setProperty("Accept", "application/json");

        sendSmtpEmail.setSender(sender);
        sendSmtpEmail.setTo(toList);
        sendSmtpEmail.setHeaders(headers);
        sendSmtpEmail.setHtmlContent(buildEmailTemplate(userVO.getFirstName(), userVO.getFirstName(), email.getToken()));
        sendSmtpEmail.setSubject("Confirmação de Cadastro");
        sendSmtpEmail.setReplyTo(sendSmtpEmailReplyTo);

        CreateSmtpEmail response = api.sendTransacEmail(sendSmtpEmail);
        return response.toString();
    }

    public String buildEmailTemplate(String firstName, String lastName, String token) {
        Context thymeleafContext = new Context();
        thymeleafContext.setVariable("firstName", firstName);
        thymeleafContext.setVariable("lastName", lastName);
        thymeleafContext.setVariable("token", token);

        return templateEngine.process("emailTemplate", thymeleafContext);
    }
}

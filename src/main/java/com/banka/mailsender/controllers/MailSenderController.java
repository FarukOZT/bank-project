package com.banka.mailsender.controllers;

import com.banka.mailsender.dto.MailRequest;
import com.banka.mailsender.dto.MailResponse;
import com.banka.mailsender.services.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MailSenderController {
    private static final Logger logger = LoggerFactory.getLogger(MailSenderController.class);
    private final MailSenderService emailService;

    @Autowired
    public MailSenderController(MailSenderService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public ResponseEntity<MailResponse> sendEmail(@RequestBody MailRequest request) {
        emailService.sendEmail(request);
        logger.info("Mail basariyla gonderildi");
        MailResponse mailResponse = new MailResponse();
        mailResponse.setTo(request.getTo());
        mailResponse.setText(request.getText());
        mailResponse.setSubject(request.getSubject());
        return new ResponseEntity<>(mailResponse, HttpStatus.OK);
    }
}
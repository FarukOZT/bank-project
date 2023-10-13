package com.banka.mailsender.services;

import com.banka.mailsender.dto.MailRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(MailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getText() + generateRandomCode(6));

        javaMailSender.send(message);
    }
    private static String generateRandomCode(int length) {
        return RandomStringUtils.randomNumeric(length);
    }
}

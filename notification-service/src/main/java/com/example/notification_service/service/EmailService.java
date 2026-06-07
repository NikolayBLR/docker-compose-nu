package com.example.notification_service.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class EmailService {

    private static final String MY_EMAIL = "bayern_123@mail.ru";

    private JavaMailSender mailSender;

    public void sendSimpleEmail(String to, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(MY_EMAIL);
        message.setSubject("Тестовое письмо");
        message.setTo(to);
        message.setText(text);

        mailSender.send(message);
    }
}

package com.biblioteca.services;

import com.biblioteca.services.interfaces.IFeedbackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class FeedbackService implements IFeedbackService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public FeedbackService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendFeedBack(String email, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(fromEmail);

        mailSender.send(message);
    }
}

package com.sumukh.fabricos.services;

import lombok.Getter;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("sumukhka94@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setSubject("Test Email");
        mailMessage.setText(body);

        mailSender.send(mailMessage);
    }
}

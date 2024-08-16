package com.nor.flightManagementSystem.service;

import com.nor.flightManagementSystem.exception.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String verificationUrl) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Email Verification");
            message.setText("Please verify your email by clicking the link: " + verificationUrl);
            mailSender.send(message);
        } catch (MailException e) {
            throw new EmailSendingException("Failed to send email", e);
        }
    }
}

package com.nor.flightManagementSystem.service;

import com.nor.flightManagementSystem.exception.EmailSendingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailService {


    private final JavaMailSender mailSender;

    public void sendVerificationEmail(String toEmail, String verificationUrl) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

            String htmlContent = "<html>" +
                    "<body style='font-family: Arial, sans-serif;'>" +
                    "<h2 style='color: #2E86C1;'>Welcome to AirSavvy!</h2>" +
                    "<p>Thank you for registering. Please verify your email address by clicking the button below:</p>" +
                    "<a href='" + verificationUrl + "' " +
                    "style='display: inline-block; padding: 10px 20px; background-color: #28a745; " +
                    "color: white; text-decoration: none; border-radius: 5px;'>Verify Email</a>" +
                    "<p>If the button doesn't work, copy and paste this link into your browser:</p>" +
                    "<p><a href='" + verificationUrl + "'>" + verificationUrl + "</a></p>" +
                    "<br/><p>— The AirSavvy Team</p>" +
                    "</body></html>";

            // IMPORTANT: Set a "from" address
            helper.setFrom("no-reply@airsavvy.com"); // change as per your domain/config!

            helper.setTo(toEmail);
            helper.setSubject("Verify Your AirSavvy Account");
            helper.setText(htmlContent, true); // true = isHtml
            mailSender.send(message);
        } catch (MailException | MessagingException e) {
            // Properly wrap any exception as the cause, do NOT cast!
            throw new EmailSendingException("Failed to send email", e);
        }
    }
}
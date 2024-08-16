package com.nor.flightManagementSystem.exception;

import org.springframework.mail.MailException;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException(String failedToSendEmail, MailException e) {
    }
}

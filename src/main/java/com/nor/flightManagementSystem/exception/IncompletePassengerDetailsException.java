package com.nor.flightManagementSystem.exception;

public class IncompletePassengerDetailsException extends RuntimeException {
    public IncompletePassengerDetailsException(String message) {
        super(message);
    }
}

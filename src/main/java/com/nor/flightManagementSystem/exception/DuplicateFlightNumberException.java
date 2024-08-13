package com.nor.flightManagementSystem.exception;

public class DuplicateFlightNumberException extends RuntimeException {
    public DuplicateFlightNumberException(String message) {
        super(message);
    }
}

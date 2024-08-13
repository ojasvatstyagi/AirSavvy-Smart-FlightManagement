package com.nor.flightManagementSystem.exception;

public class DuplicateAirportCodeException extends RuntimeException {
    public DuplicateAirportCodeException(String message) {
        super(message);
    }
}

package com.nor.flightManagementSystem.exception;

public class InvalidAirportCodeException extends RuntimeException {
    public InvalidAirportCodeException(String message) {
        super(message);
    }
}

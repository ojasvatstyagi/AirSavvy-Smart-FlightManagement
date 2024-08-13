package com.nor.flightManagementSystem.exception;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message, Exception e) {
        super(message, e);
    }

    public DatabaseException(String message) {
        super(message);
    }
}

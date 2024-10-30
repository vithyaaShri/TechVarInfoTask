package com.example.payrollservice.exceptionHandling;

public class ListIsEmptyException extends RuntimeException {
    String message;
    public ListIsEmptyException(String message) {
        super(message);
        this.message = message;
    }
}

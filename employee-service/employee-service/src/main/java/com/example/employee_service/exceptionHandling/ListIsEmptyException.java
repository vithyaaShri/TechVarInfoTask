package com.example.employee_service.exceptionHandling;

public class ListIsEmptyException extends RuntimeException {
    String message;
    public ListIsEmptyException(String message) {
        super(message);
        this.message = message;
    }
}

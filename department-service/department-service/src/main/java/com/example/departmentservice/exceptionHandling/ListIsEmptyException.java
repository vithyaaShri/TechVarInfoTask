package com.example.departmentservice.exceptionHandling;

public class ListIsEmptyException extends RuntimeException {
    String message;
    public ListIsEmptyException(String message) {
        super(message);
        this.message = message;
    }
}

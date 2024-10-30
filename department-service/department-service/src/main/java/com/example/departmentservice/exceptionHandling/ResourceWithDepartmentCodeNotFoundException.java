package com.example.departmentservice.exceptionHandling;

public class ResourceWithDepartmentCodeNotFoundException extends RuntimeException{
    public ResourceWithDepartmentCodeNotFoundException(String message) {
        super(message);
    }
}

package com.example.departmentservice.exceptionHandling;

public class ResourceWithDepartmentCodeAlreadyExist extends RuntimeException{
    public ResourceWithDepartmentCodeAlreadyExist(String message) {
        super(message);
    }
}

package com.example.employee_service.exceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class UserApiException extends RuntimeException{
    private HttpStatus httpStatus;
    private String message;
}

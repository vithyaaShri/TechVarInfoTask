package com.example.employee_service.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceWithEmailIdNotFoundException extends RuntimeException{
        private  String message;
        public  ResourceWithEmailIdNotFoundException(String message){
            super(message);
        }
    }

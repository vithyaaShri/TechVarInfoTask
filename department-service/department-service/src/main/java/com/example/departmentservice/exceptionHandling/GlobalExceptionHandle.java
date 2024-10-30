package com.example.departmentservice.exceptionHandling;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandle extends ResponseEntityExceptionHandler {

    //Resource Not Found Exception catches the exception thrown from Service class is searching based on Department id and Department detail is not Found.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    //When We search an Department based on DepartmentCode and if it is found then Already Exists Exception is thrown
    @ExceptionHandler(ResourceWithDepartmentCodeAlreadyExist.class)
    public ResponseEntity<ErrorDetails> handleResourceWithDepartmentCodeAlreadyExist(ResourceWithDepartmentCodeAlreadyExist exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT WITH CODE ALREADY FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    //Handle All the Other Exception except the above exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleExceptions(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //When We search for List Of Value and if list is empty ListIsEmptyException is thrown.
    @ExceptionHandler(ListIsEmptyException.class)
    public ResponseEntity<ErrorDetails> handleListIsEmptyException(ListIsEmptyException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT Already FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    //When We search an Department based on DepartmentCode and if it is not found then notFound Exception is thrown
    @ExceptionHandler(ResourceWithDepartmentCodeNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceWithDepartmentCodeNotFoundException(ResourceWithDepartmentCodeNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT CODE NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    //This method is to override the Exception occurring based on Spring Starter Validation.Instead of Printing the Total Stack Trace
    //It will provide only the default message and field name
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex
            , HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,String> errors=new HashMap<>();
        List<ObjectError> objecterrors= ex.getBindingResult().getAllErrors();
        objecterrors.forEach(objecterror->{
            FieldError fieldError=(FieldError)objecterror;
            errors.put(fieldError.getField(),objecterror.getDefaultMessage());
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


}

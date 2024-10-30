package com.example.employee_service.exceptionHandling;
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

    //Resource Not Found Exception catches the exception thrown from Service class is searching based on Employee id and Employee detail is not Found.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    //Resource Not Found Name Exception catches the exception thrown from Service class is searching based on Employee name and Employee detail is not Found.
    @ExceptionHandler(ResourceNotFoundNameException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundNameException(ResourceNotFoundNameException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    //UserApiException is a Spring Security Based Exception
    @ExceptionHandler(UserApiException.class)
    public ResponseEntity<ErrorDetails> handleUserApiException(UserApiException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    //When We search for List Of Value and if list is empty ListIsEmptyException is thrown.
    @ExceptionHandler(ListIsEmptyException.class)
    public ResponseEntity<ErrorDetails> handleListIsEmptyException(ListIsEmptyException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    //When We search an Employee based on Email and if it is not found then Email Not Found Exception is thrown
    @ExceptionHandler(ResourceWithEmailIdNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceWithEmailIdNotFoundException(ResourceWithEmailIdNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"DEPARTMENT NOT FOUND");
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    //When We add an Employee based  Validation should Happen Based on Email and if it is found then EmailAlreadyExist is thrown
    @ExceptionHandler(EmailAlreadyExsist.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExsist(EmailAlreadyExsist exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"Email_Already_Exsist");
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }
    //Handle rest of exception Not Handled by other Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleExceptions(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails=new ErrorDetails(LocalDateTime.now(),exception.getMessage(),webRequest.getDescription(false),"INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
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

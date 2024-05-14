package com.example.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;


@ControllerAdvice
public class GlobalHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorDetails> arithmeticException(ArithmeticException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("Dividing by zero not allowed");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> resourceNotFoundException(ResourceNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("Record for not found ");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity<ErrorDetails> operationNotFoundException(OperationNotFoundException ex) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTitle("Operation is not correct");
        errorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetails.setDetails(ex.getMessage());
        errorDetails.setTimeStamp(new Date().getTime());
        errorDetails.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

}

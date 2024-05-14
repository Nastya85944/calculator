package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class OperationNotFoundException extends Exception{

    public OperationNotFoundException(String message) {
        super(message);
    }
}

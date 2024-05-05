package com.example.demo.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ErrorDetails {

    private String title;
    private int status;
    private String details;
    private long timeStamp;
    private String developerMessage;
    private List<String> validationErrors;
}

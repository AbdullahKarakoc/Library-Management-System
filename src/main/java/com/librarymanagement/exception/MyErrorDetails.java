package com.librarymanagement.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class MyErrorDetails {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public  MyErrorDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    public  MyErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }



}

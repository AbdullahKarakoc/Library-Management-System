package com.librarymanagement.exception;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundException(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handlePermissionException(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }
}
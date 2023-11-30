package com.example.javapractice.restaurant.controller.exceptions;

import com.example.javapractice.restaurant.exceptions.ArgumentException;
import com.example.javapractice.restaurant.exceptions.ResourceIsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

// ControllerAdvice is annotation that helps to handl ANY exception the way you want
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler({ResourceIsNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorMessage handleResourceNotFoundException(ResourceIsNotFoundException exception) {
        return new ErrorMessage(exception.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({ArgumentException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorMessage handleArgumentException(ArgumentException exception) {
        return new ErrorMessage(exception.getMessage());
    }
}

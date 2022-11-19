package com.example.softdownloaderapi.controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.softdownloaderapi.model.ErrorValidateMessage;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorValidateMessage validateException(ConstraintViolationException ex){
        return new ErrorValidateMessage(400, ex.getMessage());
    }
}

package com.trainingapps.userms.controller;

import com.trainingapps.userms.exceptions.IncorrectCredentialsException;
import com.trainingapps.userms.exceptions.InvalidTokenException;
import com.trainingapps.userms.exceptions.UserNotFoundException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFound(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    public String handleInvalid(Exception e) {
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({
            IncorrectCredentialsException.class
    })
    public String handleIncorrectCredentials(Exception e) {
        return e.getMessage();
    }

}

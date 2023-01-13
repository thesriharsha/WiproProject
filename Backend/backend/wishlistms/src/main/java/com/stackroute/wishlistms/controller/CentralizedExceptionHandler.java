package com.stackroute.wishlistms.controller;

import com.stackroute.wishlistms.exceptions.CityInfoAlreadyExistsException;
import com.stackroute.wishlistms.exceptions.CityInfoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class CentralizedExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CityInfoNotFoundException.class)
    public String handleCityInfoNotFound(CityInfoNotFoundException e) {
        String msg = e.getMessage();
        return msg;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CityInfoAlreadyExistsException.class)
    public String handelCityInfoAlreadyExists(Exception e) {
        String msg = e.getMessage();
        return msg;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class})
    public String handelInvalid(Exception e) {
        String msg = e.getMessage();
        return msg;
    }

}

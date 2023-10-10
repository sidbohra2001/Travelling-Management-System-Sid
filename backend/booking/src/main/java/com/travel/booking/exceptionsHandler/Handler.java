package com.travel.booking.exceptionsHandler;

import com.travel.booking.exceptions.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleAlreadyExistsException(AlreadyExistsException e) {
        return Format.builder().status(BAD_REQUEST.name()).message(e.getMessage()).build();
    }

    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleNoDataFoundException(NoDataFoundException e) {
        return Format.builder().status(BAD_REQUEST.name()).message(e.getMessage()).build();
    }

    @ExceptionHandler(RestClientResponseException.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleRestClientResponseException(RestClientResponseException e) {
        return Format.builder().status(BAD_REQUEST.name()).message(e.getResponseBodyAs(Format.class).getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleMethodArgumentException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append(fieldError.getField() + " : " + fieldError.getDefaultMessage() + "\n");
        }
        return Format.builder().status(BAD_REQUEST.name()).message(sb.toString()).build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleRuntimeException(RuntimeException e) {
        return Format.builder().status(BAD_REQUEST.name()).message(e.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(BAD_REQUEST)
    public Format handleException(Exception e) {
        return Format.builder().status(BAD_REQUEST.name()).message(e.getMessage()).build();
    }
}

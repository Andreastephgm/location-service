package com.hogar360.location_service.application.exception;

import com.hogar360.location_service.domain.exceptions.LocationAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler(LocationAlreadyExistsException.class)
    public ResponseEntity<String> handleLocationAlreadyExists(LocationAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }
}

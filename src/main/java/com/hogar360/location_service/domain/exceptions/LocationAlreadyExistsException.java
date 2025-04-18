package com.hogar360.location_service.domain.exceptions;

public class LocationAlreadyExistsException extends RuntimeException{
    public LocationAlreadyExistsException(String message){
        super(message);
    }
}

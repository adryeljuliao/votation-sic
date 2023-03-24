package com.votation.api.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends APIException{

    public NotFoundException(String error) {
        super(null, HttpStatus.NOT_FOUND, error);
    }
}

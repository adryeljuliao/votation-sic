package com.votation.api.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends APIException{

    public BadRequestException(String error) {
        super(null, HttpStatus.BAD_REQUEST, error);
    }
}

package com.votation.api.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class APIException extends RuntimeException{

    private final HttpStatus status;
    private final String error;

    public APIException(Throwable cause, HttpStatus status, String error) {
        super(cause);
        this.status = status;
        this.error = error;
    }
}

package com.votation.api.controllers.exceptions;

import com.votation.api.exceptions.APIException;
import com.votation.api.exceptions.BadRequestException;
import com.votation.api.exceptions.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.DataException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handlerAPIException(@NonNull final APIException ex) {
        log.error("m=handlerAPIException msg={}", ex.getError());
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handlerNotFoundException(@NonNull final NotFoundException ex) {
        log.error("m=handlerNotFoundException msg={}", ex.getError());
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handlerBadRequestException(@NonNull final BadRequestException ex) {
        log.error("m=handlerBadRequestException msg={}", ex.getError());
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

    @ExceptionHandler(DataException.class)
    public ResponseEntity<?> handlerDataException(@NonNull final DataException ex) {
        log.error("m=handlerDataException msg={}", ex.getCause());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}

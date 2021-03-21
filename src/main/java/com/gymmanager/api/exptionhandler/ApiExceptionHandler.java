package com.gymmanager.api.exptionhandler;

import com.gymmanager.api.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.api.exptionhandler.exceptions.NotFoundExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handlerNotFoundException(NotFoundException notFoundException) {
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Not found exception. Check Documentation")
                        .timestamp(OffsetDateTime.now(ZoneOffset.UTC))
                        .details(notFoundException.getMessage())
                        .developerMessage("The resource doesn't exist")
                        .build(), HttpStatus.NOT_FOUND
        );
    }
}

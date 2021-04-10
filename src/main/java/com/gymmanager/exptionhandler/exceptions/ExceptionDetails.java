package com.gymmanager.exptionhandler.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected int status;
    protected String title;
    protected String details;
    protected String developerMessage;
    protected LocalDateTime timestamp;
}

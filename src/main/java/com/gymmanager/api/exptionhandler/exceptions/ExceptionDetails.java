package com.gymmanager.api.exptionhandler.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected int status;
    protected String title;
    protected String details;
    protected String developerMessage;
    protected OffsetDateTime timestamp;
}

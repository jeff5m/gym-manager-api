package com.gymmanager.exptionhandler.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    @Schema(description = "Response status code")
    protected int status;
    @Schema(description = "Error title")
    protected String title;
    @Schema(description = "Error details")
    protected String details;
    @Schema(description = "Developer's message")
    protected String developerMessage;
    @Schema(description = "Error time and date")
    protected LocalDateTime timestamp;
}

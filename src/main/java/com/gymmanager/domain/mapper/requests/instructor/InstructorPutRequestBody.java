package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.controller.validations.UniqueFieldInstructor;
import com.gymmanager.domain.model.InstructorServices;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorPutRequestBody {

    @NotBlank(message = "Instructor must have a valid avatar URL")
    @URL
    @Schema(description = "This is the Instructor's profile photo link",
            example = "https://some_url.com",
            required = true)
    private String avatarUrl;

    @NotBlank(message = "Instructor must have an email")
    @Size(message = "Email length must be between 0 and 100 characters", max = 60)
    @Email
    @UniqueFieldInstructor(message = "There is already an instructor registered with this email")
    @Schema(description = "This is the Instructor's email",
            example = "test@email.com",
            maxLength = 60,
            required = true)
    private String email;

    @NotNull(message = "Instructor must teach something. Check documentation to see available services")
    @Enumerated(EnumType.STRING)
    @Schema(description = "This is the Instructor's specialty",
            example = "PILATES",
            required = true)
    private InstructorServices services;
}

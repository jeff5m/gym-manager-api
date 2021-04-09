package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.controller.validations.UniqueFieldInstructor;
import com.gymmanager.domain.model.InstructorServices;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorPostRequestBody {

    @NotBlank(message = "Instructor must have a name")
    @Size(message = "Name length must be between 0 and 100 characters", max = 100)
    @Schema(description = "This is the Instructor's name",
            example = "John Dear",
            maxLength = 100,
            required = true)
    private String name;

    @NotBlank(message = "Instructor must have a valid avatar URL")
    @URL
    @Schema(description = "This is the Instructor's profile photo link",
            example = "https://some_url.com",
            required = true)
    private String avatarUrl;

    @NotBlank(message = "Instructor must have an email")
    @Size(message = "Email length must be between 0 and 100 characters", max = 60)
    @Email(message = "The informed email is in an invalid format")
    @UniqueFieldInstructor(message = "There is already an instructor registered with this email")
    @Schema(description = "This is the Instructor's email",
            example = "test@email.com",
            maxLength = 60,
            required = true)
    private String email;

    @NotEmpty(message = "Instructor must have a cpf")
    @CPF(message = "The 'cpf' field must contains 11 numbers")
    @UniqueFieldInstructor(message = "There is already an instructor registered with this cpf")
    @Schema(description = "This is the Instructor's cpf",
            example = "12345678910",
            maxLength = 11,
            required = true)
    private String cpf;

    @NotNull(message = "Instructor must teach something. Check documentation to see available services")
    @Enumerated(EnumType.STRING)
    @Schema(description = "This is the Instructor's specialty",
            example = "PILATES",
            required = true)
    private InstructorServices services;

    @NotNull(message = "Instructor must have a birth date")
    @Past(message = "Must be a past date")
    @Schema(description = "This is the Instructor's birth date. The format is yyyy-MM-dd",
            example = "1991-07-03",
            required = true)
    private LocalDate birth;
}

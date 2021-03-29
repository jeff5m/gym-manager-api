package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.controller.validations.UniqueFieldInstructor;
import com.gymmanager.domain.model.InstructorServices;
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
    private String name;

    @NotBlank(message = "Instructor must have a valid avatar URL")
    @URL
    private String avatarUrl;

    @NotBlank(message = "Instructor must have an email")
    @Size(message = "Email length must be between 0 and 100 characters", max = 60)
    @Email(message = "The informed email is in an invalid format")
    @UniqueFieldInstructor(message = "There is already an instructor registered with this email")
    private String email;

    @NotEmpty(message = "Instructor must have a cpf")
    @CPF(message = "The 'cpf' field must contains 11 numbers")
    @UniqueFieldInstructor(message = "There is already an instructor registered with this cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    private InstructorServices services;

    @NotNull(message = "Instructor must have a birth date")
    @Past(message = "Must be a past date")
    private LocalDate birth;
}

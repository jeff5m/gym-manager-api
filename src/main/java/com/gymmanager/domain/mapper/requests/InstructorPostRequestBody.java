package com.gymmanager.domain.mapper.requests;

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
    private String name;

    @NotBlank(message = "Instructor must have a valid avatar URL")
    @URL
    private String avatarUrl;

    @NotBlank(message = "Instructor must have an email")
    @Email
    private String email;

    @NotEmpty
    @CPF(message = "The 'cpf' field must contains 11 numbers")
    private String cpf;

    @Enumerated(EnumType.STRING)
    private InstructorServices services;

    @NotNull(message = "Instructor must have a birth date")
    @Past(message = "Must be a past date")
    private LocalDate birth;
}

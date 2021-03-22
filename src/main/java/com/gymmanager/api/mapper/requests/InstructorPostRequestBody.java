package com.gymmanager.api.mapper.requests;

import com.gymmanager.domain.model.InstructorServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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

    @NotBlank(message = "Instructor must have a valid CPF")
    @CPF
    private String cpf;

    @Enumerated(EnumType.STRING)
    private InstructorServices services;

    @NotNull(message = "Instructor must have birth date")
    @PastOrPresent
    private LocalDate birth;
}

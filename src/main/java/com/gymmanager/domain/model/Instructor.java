package com.gymmanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private LocalDate birth;

    private LocalDate createdAt;
}

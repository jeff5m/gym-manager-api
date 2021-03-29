package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.controller.validations.Unique;
import com.gymmanager.domain.model.InstructorServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorPutRequestBody {

    @NotBlank(message = "Instructor must have a valid avatar URL")
    @URL
    private String avatarUrl;

    @NotBlank(message = "Instructor must have an email")
    @Email
    @Unique(message = "There is already an instructor registered with this email")
    private String email;

    @Enumerated(EnumType.STRING)
    private InstructorServices services;
}

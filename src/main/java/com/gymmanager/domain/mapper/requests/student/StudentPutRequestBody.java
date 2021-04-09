package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.controller.validations.UniqueFieldStudent;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPutRequestBody {
    @NotBlank(message = "Student must have a valid avatar URL")
    @URL
    @Schema(description = "This is the Student's profile photo link",
            example = "https://some_url.com",
            required = true)
    private String avatarUrl;
    @NotBlank(message = "Student must have an email")
    @Email
    @UniqueFieldStudent(message = "There is already a student registered with this email")
    @Schema(description = "This is the Student's email",
            example = "student@email.com",
            maxLength = 60,
            required = true)
    private String email;
}

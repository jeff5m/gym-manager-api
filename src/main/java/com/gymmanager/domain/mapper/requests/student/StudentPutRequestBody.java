package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.controller.validations.UniqueFieldStudent;
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
    private String avatarUrl;
    @NotBlank(message = "Student must have an email")
    @Email
    @UniqueFieldStudent(message = "There is already a student registered with this email")
    private String email;
}

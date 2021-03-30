package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.controller.validations.UniqueFieldStudent;
import com.gymmanager.domain.model.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPostRequestBody {

    @NotBlank(message = "Student must have a name")
    @Size(message = "Name length must be between 0 and 100 characters", max = 100)
    private String name;
    @NotBlank(message = "Student must have a valid avatar URL")
    @URL
    private String avatarUrl;
    @NotBlank(message = "Student must have an email")
    @Size(message = "Email length must be between 0 and 100 characters", max = 60)
    @Email
    @UniqueFieldStudent(message = "There is already an student registered with this email")
    private String email;
    @NotEmpty(message = "Student must have a cpf")
    @CPF(message = "The 'cpf' field must contains 11 numbers")
    @UniqueFieldStudent(message = "There is already an student registered with this cpf")
    private String cpf;
    @NotNull(message = "Student must have a weight")
    @Positive(message = "Weight must be a positive number")
    private BigDecimal weight;
    @NotNull(message = "Student must have a height")
    @Positive(message = "Height must be a positive number")
    private BigDecimal height;
    @NotNull(message = "Student must have an age")
    @Positive(message = "Students age must be greater then zero")
    private Integer age;
    @NotNull(message = "Student must have a gender")
    @Enumerated(EnumType.STRING)
    private StudentGender gender;
    @NotNull(message = "Student must have an Instructor")
    private StudentPostInstructorId instructor;
}

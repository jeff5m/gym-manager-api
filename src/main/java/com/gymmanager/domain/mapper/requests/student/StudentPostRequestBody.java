package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.controller.validations.UniqueFieldStudent;
import com.gymmanager.domain.model.StudentGender;
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
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentPostRequestBody {

    @NotBlank(message = "Student must have a name")
    @Size(message = "Name length must be between 0 and 100 characters", max = 100)
    @Schema(description = "This is the Student's name",
            example = "Anna Wright",
            maxLength = 100,
            required = true)
    private String name;

    @NotBlank(message = "Student must have a valid avatar URL")
    @URL
    @Schema(description = "This is the Student's profile photo link",
            example = "https://some_url.com",
            required = true)
    private String avatarUrl;

    @NotBlank(message = "Student must have an email")
    @Size(message = "Email length must be between 0 and 100 characters", max = 60)
    @Email
    @UniqueFieldStudent(message = "There is already an student registered with this email")
    @Schema(description = "This is the Student's email",
            example = "student@email.com",
            maxLength = 60,
            required = true)
    private String email;

    @NotEmpty(message = "Student must have a cpf")
    @CPF(message = "The 'cpf' field must contains 11 numbers")
    @UniqueFieldStudent(message = "There is already an student registered with this cpf")
    @Schema(description = "This is the Student's cpf",
            example = "12345678910",
            maxLength = 11,
            required = true)
    private String cpf;

    @NotNull(message = "Student must have a weight")
    @Positive(message = "Weight must be a positive number")
    @Digits(integer = 3, fraction = 1)
    @Schema(description = "This is the Student's weight. Must have one decimal number",
            example = "128.8",
            required = true)
    private BigDecimal weight;

    @NotNull(message = "Student must have a height")
    @Positive(message = "Height must be a positive number")
    @Digits(integer = 3, fraction = 1)
    @Schema(description = "This is the Student's weight. Must have one decimal number",
            example = "174.1",
            required = true)
    private BigDecimal height;

    @NotNull(message = "Student must have an age")
    @Positive(message = "Students age must be greater then zero")
    @Schema(description = "This is the Student's age",
            example = "38",
            required = true)
    private Integer age;

    @NotNull(message = "Student must have a gender")
    @Enumerated(EnumType.STRING)
    @Schema(description = "This is the Student's gender",
            example = "F",
            required = true)
    private StudentGender gender;

    @NotNull(message = "Student must have an Instructor")
    @Schema(description = "This is an object instructor that contains Student's Instructor id",
            example = "instructor: {'id': 2}",
            required = true)
    private StudentPostInstructorId instructor;
}

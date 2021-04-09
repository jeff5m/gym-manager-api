package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.domain.model.StudentGender;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorStudentClientResponseBody {
    @Schema(description = "This is the Student's id",
            example = "40")
    private Long id;
    @Schema(description = "This is the Student's name",
            example = "Anna Wright")
    private String name;
    @Schema(description = "This is the Student's age",
            example = "28")
    private Integer age;
    @Schema(description = "This is the Student's gender",
            example = "M")
    private StudentGender gender;
    @Schema(description = "This is the Student's profile photo link",
            example = "https://some_url.com")
    private String avatarUrl;
}


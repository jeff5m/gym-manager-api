package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentClientResponseBody {
    @Schema(description = "This is the Student's id",
            example = "40")
    private Long id;
    @Schema(description = "This is the Student's name",
            example = "Anna Wright")
    private String name;
    @Schema(description = "This is the Student's profile photo link",
            example = "https://some_url.com")
    private String avatarUrl;
    @Schema(description = "This is the Student's Instructor",
            example = "John Dear")
    private InstructorClientResponseBody instructor;
}

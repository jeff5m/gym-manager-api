package com.gymmanager.domain.mapper.requests.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentPostInstructorId {
    @Schema(description = "This is the Student's Instructor id",
            example = "2",
            required = true)
    private Long id;
}

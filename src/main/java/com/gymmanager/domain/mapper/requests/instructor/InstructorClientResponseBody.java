package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.domain.model.InstructorServices;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorClientResponseBody {
    @Schema(description = "This is the Instructor's id",
            example = "1")
    private Long id;
    @Schema(description = "This is the Instructor's name",
            example = "John Dear")
    private String name;
    @Schema(description = "This is the Instructor's profile photo link",
            example = "https://some_url.com")
    private String avatarUrl;
    @Schema(description = "This is the Instructor's specialty",
            example = "PILATES")
    private InstructorServices services;
    @Schema(description = "This is the Instructor's birth date. The format is yyyy-MM-dd",
            example = "1991-07-03")
    private LocalDate createdAt;
    @Schema(description = "This is the number of students of this Instructor",
            example = "5")
    private Long numberOfStudents;
}


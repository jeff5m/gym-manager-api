package com.gymmanager.util.instructor;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.model.InstructorServices;

import java.time.LocalDate;

public class InstructorClientResponseBodyCreator {
    public static InstructorClientResponseBody validInstructorClientResponseBody() {
        return InstructorClientResponseBody.builder()
                .id(1L)
                .name("Jane Doe Instructor")
                .avatarUrl("https://asdf.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .createdAt(LocalDate.now())
                .numberOfStudents(0L)
                .build();
    }

    public static InstructorClientResponseBody validUpdatedInstructorClientResponseBody() {
        return InstructorClientResponseBody.builder()
                .id(1L)
                .name("Jane Doe Instructor")
                .avatarUrl("https://instructorUpdated.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .createdAt(LocalDate.now())
                .numberOfStudents(0L)
                .build();
    }
}

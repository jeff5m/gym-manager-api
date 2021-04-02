package com.gymmanager.util.instructor;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.model.InstructorServices;

import java.time.LocalDate;

public class InstructorClientResponseBodyCreator {
    public static InstructorClientResponseBody validInstructorClientResponseBody() {
        return InstructorClientResponseBody.builder()
                .name("Jane Doe Instructor")
                .avatarUrl("https://asdf.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .createdAt(LocalDate.now())
                .numberOfStudents(0L)
                .build();
    }

    public static InstructorClientResponseBody validUpdatedInstructorClientResponseBody() {
        return InstructorClientResponseBody.builder()
                .name("Jane Doe Instructor Updated")
                .avatarUrl("https://updated.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .createdAt(LocalDate.now())
                .numberOfStudents(0L)
                .build();
    }
}

package com.gymmanager.util.student;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.util.instructor.InstructorClientResponseBodyCreator;

public class StudentClientResponseBodyCreator {
    public static StudentClientResponseBody validStudentClientResponseBody() {
        return StudentClientResponseBody.builder()
                .id(1L)
                .name("Jane Doe Student")
                .avatarUrl("https://asdf.com")
                .instructor(InstructorClientResponseBodyCreator.validInstructorClientResponseBody())
                .build();
    }

    public static StudentClientResponseBody validUpdatedStudentClientResponseBody() {
        return StudentClientResponseBody.builder()
                .id(1L)
                .name("Jane Doe Student")
                .avatarUrl("https://updated.com")
                .instructor(InstructorClientResponseBodyCreator.validInstructorClientResponseBody())
                .build();
    }
}

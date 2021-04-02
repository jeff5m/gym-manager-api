package com.gymmanager.util.instructor;

import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.model.StudentGender;

public class InstructorStudentsClientResponseBodyCreator {
    public static InstructorStudentClientResponseBody validInstructorStudentClientResponseBody() {
        return InstructorStudentClientResponseBody.builder()
                .id(1L)
                .name("Jane Doe Student")
                .avatarUrl("https://asdf.com")
                .age(28)
                .gender(StudentGender.valueOf("F"))
                .build();
    }
}

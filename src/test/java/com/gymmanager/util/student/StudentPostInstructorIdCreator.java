package com.gymmanager.util.student;

import com.gymmanager.domain.mapper.requests.student.StudentPostInstructorId;

public class StudentPostInstructorIdCreator {
    public static StudentPostInstructorId validStudentPostInstructorId() {
        return StudentPostInstructorId.builder()
                .id(1L)
                .build();
    }
}

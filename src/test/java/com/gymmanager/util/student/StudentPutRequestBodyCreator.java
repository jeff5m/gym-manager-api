package com.gymmanager.util.student;

import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;

public class StudentPutRequestBodyCreator {
    public static StudentPutRequestBody validStudentPutRequestBody() {
        return StudentPutRequestBody.builder()
                .avatarUrl(StudentCreator.validUpdatedStudent().getAvatarUrl())
                .email(StudentCreator.validUpdatedStudent().getEmail())
                .build();
    }
}

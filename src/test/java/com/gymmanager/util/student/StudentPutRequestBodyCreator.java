package com.gymmanager.util.student;

import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;

public class StudentPutRequestBodyCreator {
    public static StudentPutRequestBody validStudentPutRequestBody() {
        return StudentPutRequestBody.builder()
                .avatarUrl(StudentCreator.validStudentToBeSaved().getAvatarUrl())
                .email(StudentCreator.validStudentToBeSaved().getEmail())
                .build();
    }
}

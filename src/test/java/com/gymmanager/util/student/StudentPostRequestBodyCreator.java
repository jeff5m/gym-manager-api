package com.gymmanager.util.student;

import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;

public class StudentPostRequestBodyCreator {
    public static StudentPostRequestBody validStudentPostRequestBody() {
        return StudentPostRequestBody.builder()
                .name(StudentCreator.validStudentToBeSaved().getName())
                .avatarUrl(StudentCreator.validStudentToBeSaved().getAvatarUrl())
                .email(StudentCreator.validStudentToBeSaved().getEmail())
                .cpf(StudentCreator.validStudentToBeSaved().getCpf())
                .weight(StudentCreator.validStudentToBeSaved().getWeight())
                .height(StudentCreator.validStudentToBeSaved().getHeight())
                .age(StudentCreator.validStudentToBeSaved().getAge())
                .gender(StudentCreator.validStudentToBeSaved().getGender())
                .instructor(StudentPostInstructorIdCreator.validStudentPostInstructorId())
                .build();
    }
}

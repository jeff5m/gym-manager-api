package com.gymmanager.util.instructor;

import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.model.InstructorServices;

public class InstructorPutRequestBodyCreator {
    public static InstructorPutRequestBody validInstructorPutRequestBody() {
        return InstructorPutRequestBody.builder()
                .avatarUrl("https://instructorUpdated.com")
                .email("test@email.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .build();
    }
}

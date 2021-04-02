package com.gymmanager.util.instructor;

import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.model.InstructorServices;

import java.time.LocalDate;
import java.time.Month;

public class InstructorPutRequestBodyCreator {
    public static InstructorPutRequestBody validInstructorPutRequestBody() {
        return InstructorPutRequestBody.builder()
                .avatarUrl("https://asdf.com")
                .email("test@email.com")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .build();
    }
}

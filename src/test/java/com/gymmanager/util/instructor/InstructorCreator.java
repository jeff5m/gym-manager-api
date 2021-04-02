package com.gymmanager.util.instructor;

import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.model.InstructorServices;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

public class InstructorCreator {
    public static Instructor validInstructor() {
        return Instructor.builder()
                .id(1L)
                .name("Jane Doe Instructor")
                .avatarUrl("https://asdf.com")
                .email("test@email.com")
                .cpf("70669545139")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .birth(LocalDate.of(1991, Month.SEPTEMBER, 3))
                .createdAt(LocalDate.now())
                .students(Collections.emptyList())
                .build();
    }

    public static Instructor validInstructorToBeSaved() {
        return Instructor.builder()
                .name("Jane Doe Instructor")
                .avatarUrl("https://asdf.com")
                .email("test@email.com")
                .cpf("70669545139")
                .services(InstructorServices.valueOf("BODYBUILDING"))
                .birth(LocalDate.of(1991, Month.SEPTEMBER, 3))
                .createdAt(LocalDate.now())
                .students(Collections.emptyList())
                .build();
    }
}

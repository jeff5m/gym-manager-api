package com.gymmanager.util.student;

import com.gymmanager.domain.model.Student;
import com.gymmanager.domain.model.StudentGender;
import com.gymmanager.util.instructor.InstructorCreator;

import java.math.BigDecimal;

public class StudentCreator {

    public static Student validStudent() {
        return Student.builder()
                .id(1L)
                .name("Jane Doe Student")
                .avatarUrl("https://asdf.com")
                .email("test@email.com")
                .cpf("95611559239")
                .weight(BigDecimal.valueOf(90))
                .height(BigDecimal.valueOf(165))
                .age(20)
                .gender(StudentGender.valueOf("M"))
                .instructor(InstructorCreator.validInstructor())
                .build();
    }

    public static Student validUpdatedStudent() {
        return Student.builder()
                .id(1L)
                .name("Jane Doe Student")
                .avatarUrl("https://updated.com")
                .email("test@email.com")
                .cpf("95611559239")
                .weight(BigDecimal.valueOf(90))
                .height(BigDecimal.valueOf(165))
                .age(20)
                .gender(StudentGender.valueOf("M"))
                .instructor(InstructorCreator.validInstructor())
                .build();
    }

    public static Student validStudentToBeSaved() {
        return Student.builder()
                .name("Jane Doe Student")
                .avatarUrl("https://asdf.com")
                .email("test@email.com")
                .cpf("95611559239")
                .weight(BigDecimal.valueOf(90))
                .height(BigDecimal.valueOf(165))
                .age(20)
                .gender(StudentGender.valueOf("M"))
                .instructor(InstructorCreator.validInstructor())
                .build();
    }
}

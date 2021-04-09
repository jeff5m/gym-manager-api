package com.gymmanager;

import com.gymmanager.controller.InstructorController;
import com.gymmanager.controller.StudentController;
import com.gymmanager.repository.InstructorRepository;
import com.gymmanager.repository.StudentRepository;
import com.gymmanager.service.InstructorService;
import com.gymmanager.service.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@AutoConfigureTestDatabase
class GymManagerApplicationTests {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentController studentController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorController instructorController;
    @Autowired
    private InstructorService instructorService;

    @Test
    void contextLoads() {
        Assertions.assertThat(studentController).isNotNull();
        Assertions.assertThat(studentService).isNotNull();
        Assertions.assertThat(studentRepository).isNotNull();
        Assertions.assertThat(instructorController).isNotNull();
        Assertions.assertThat(instructorService).isNotNull();
        Assertions.assertThat(instructorRepository).isNotNull();
    }

}

package com.gymmanager.integration;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.repository.InstructorRepository;
import com.gymmanager.repository.StudentRepository;
import com.gymmanager.util.instructor.InstructorCreator;
import com.gymmanager.util.student.StudentCreator;
import com.gymmanager.util.student.StudentPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StudentControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    @DisplayName("listAll returns list of students when successful")
    void list_ReturnsListOfStudents_WhenSuccessful() {
        instructorRepository.save(InstructorCreator.validInstructorToBeSaved());
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        Student studentSaved = studentRepository.save(studentToBeSaved);
        ResponseEntity<List<StudentClientResponseBody>> students = testRestTemplate.exchange(
                "/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Assertions.assertThat(students).isNotNull();
        Assertions.assertThat(students.getBody())
                .isNotEmpty()
                .isNotNull()
                .hasSize(1);
        Assertions.assertThat(students.getBody().get(0).getName()).isEqualTo(studentSaved.getName());
        Assertions.assertThat(students.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("listAll returns empty list of students when no student is found")
    void list_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        List<StudentClientResponseBody> students = testRestTemplate.exchange(
                "/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentClientResponseBody>>() {
                }).getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns student when successful")
    void save_ReturnsStudent_WhenSuccessful() {
        instructorRepository.save(InstructorCreator.validInstructorToBeSaved());
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        ResponseEntity<StudentClientResponseBody> savedStudent = testRestTemplate.postForEntity(
                "/students",
                studentToBeSaved,
                StudentClientResponseBody.class
        );

        Assertions.assertThat(savedStudent).isNotNull();
        Assertions.assertThat(savedStudent.getBody()).isNotNull();
        Assertions.assertThat(savedStudent.getBody().getId()).isNotNull();
        Assertions.assertThat(savedStudent.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("replace updates student when successful")
    void replace_UpdatesStudent_WhenSuccessful() {
        instructorRepository.save(InstructorCreator.validInstructorToBeSaved());
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        Student studentSaved = studentRepository.save(studentToBeSaved);
        Long expectedId = studentSaved.getId();
        StudentPutRequestBody studentPutRequestBody = StudentPutRequestBodyCreator.validStudentPutRequestBody();
        String url = String.format("/students/%d", expectedId);
        ResponseEntity<StudentClientResponseBody> updatedStudent = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(studentPutRequestBody),
                StudentClientResponseBody.class
        );

        Assertions.assertThat(updatedStudent).isNotNull();
        Assertions.assertThat(updatedStudent.getBody()).isNotNull();
        Assertions.assertThat(updatedStudent.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete removes student when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        instructorRepository.save(InstructorCreator.validInstructorToBeSaved());
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        Student studentSaved = studentRepository.save(studentToBeSaved);
        ResponseEntity<Void> deletedStudent = testRestTemplate.exchange(
                "/students/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                studentSaved.getId()
        );

        Assertions.assertThat(deletedStudent).isNotNull();
        Assertions.assertThat(deletedStudent.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

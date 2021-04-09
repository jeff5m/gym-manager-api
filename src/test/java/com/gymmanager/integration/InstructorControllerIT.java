package com.gymmanager.integration;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.model.Student;
import com.gymmanager.repository.InstructorRepository;
import com.gymmanager.repository.StudentRepository;
import com.gymmanager.util.instructor.InstructorCreator;
import com.gymmanager.util.instructor.InstructorPostRequestBodyCreator;
import com.gymmanager.util.instructor.InstructorPutRequestBodyCreator;
import com.gymmanager.util.student.StudentCreator;
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
class InstructorControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    @DisplayName("listAll returns list of instructors when successful")
    void list_ReturnsListOfInstructors_WhenSuccessful() {
        Instructor savedInstructor = instructorRepository.save(InstructorCreator.validInstructorToBeSaved());
        String expectedName = savedInstructor.getName();

        List<InstructorClientResponseBody> instructors = testRestTemplate.exchange(
                "/instructors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstructorClientResponseBody>>() {
                }).getBody();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(instructors.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns empty list when no instructor is found")
    void list_ReturnsEmptyListOfInstructors_WhenNoInstructorIsFound() {
        List<InstructorClientResponseBody> instructors = testRestTemplate.exchange(
                "/instructors",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstructorClientResponseBody>>() {
                }).getBody();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns instructor when successful")
    void findById_ReturnsInstructor_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);
        Long expectedId = instructorSaved.getId();
        InstructorClientResponseBody foundedInstructor = testRestTemplate.getForObject(
                "/instructors/{id}",
                InstructorClientResponseBody.class,
                expectedId
        );

        Assertions.assertThat(foundedInstructor)
                .isNotNull();
        Assertions.assertThat(foundedInstructor.getId())
                .isNotNull()
                .isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findById returns status code not found when when no instructor is found")
    void findById_ReturnsStatusCodeNotFound_WhenNoInstructorIsFound() {
        Instructor instructor = InstructorCreator.validInstructor();
        Long expectedId = instructor.getId();
        ResponseEntity<InstructorClientResponseBody> foundedInstructor = testRestTemplate.getForEntity(
                "/instructors/{id}",
                InstructorClientResponseBody.class,
                expectedId
        );

        Assertions.assertThat(foundedInstructor)
                .isNotNull();
        Assertions.assertThat(foundedInstructor.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DisplayName("listAllStudentsById returns list of students of a given instructor when successful")
    void listAllStudentsById_ReturnsListOfStudents_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);
        Long expectedId = instructorSaved.getId();
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        Student studentSaved = studentRepository.save(studentToBeSaved);
        String url = String.format("/instructors/%d/students", expectedId);

        List<InstructorStudentClientResponseBody> students = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstructorStudentClientResponseBody>>() {
                }).getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(students.get(0).getId())
                .isEqualTo(studentSaved.getId());
    }

    @Test
    @DisplayName("listAllStudentsById returns empty list of students of a given instructor when no student is found")
    void listAllStudentsById_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);
        Long expectedId = instructorSaved.getId();
        String url = String.format("/instructors/%d/students", expectedId);
        List<InstructorStudentClientResponseBody> students = testRestTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InstructorStudentClientResponseBody>>() {
                }).getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns instructor when successful")
    void save_ReturnsInstructor_WhenSuccessful() {
        InstructorPostRequestBody instructorToBeSaved = InstructorPostRequestBodyCreator.validInstructorPostRequestBody();
        ResponseEntity<InstructorClientResponseBody> instructor = testRestTemplate.postForEntity(
                "/instructors",
                instructorToBeSaved,
                InstructorClientResponseBody.class
        );

        Assertions.assertThat(instructor).isNotNull();
        Assertions.assertThat(instructor.getBody()).isNotNull();
        Assertions.assertThat(instructor.getBody().getId()).isNotNull();
        Assertions.assertThat(instructor.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    @DisplayName("replace updates instructor when successful")
    void replace_UpdatesInstructor_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);
        Long expectedId = instructorSaved.getId();
        InstructorPutRequestBody instructorPutRequestBody = InstructorPutRequestBodyCreator.validInstructorPutRequestBody();
        String url = String.format("/instructors/%d", expectedId);
        ResponseEntity<InstructorClientResponseBody> updatedInstructor = testRestTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(instructorPutRequestBody),
                InstructorClientResponseBody.class
        );

        Assertions.assertThat(updatedInstructor).isNotNull();
        Assertions.assertThat(updatedInstructor.getBody()).isNotNull();
        Assertions.assertThat(updatedInstructor.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("delete removes instructor when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = instructorRepository.save(instructorToBeSaved);
        ResponseEntity<Void> deletedInstructor = testRestTemplate.exchange(
                "/instructors/{id}",
                HttpMethod.DELETE,
                null,
                Void.class,
                instructorSaved.getId()
        );

        Assertions.assertThat(deletedInstructor).isNotNull();
        Assertions.assertThat(deletedInstructor.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

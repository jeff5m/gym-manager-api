package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.service.InstructorService;
import com.gymmanager.util.instructor.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
class InstructorControllerTest {

    @InjectMocks
    private InstructorController instructorController;

    @Mock
    private InstructorService instructorServiceMock;

    @BeforeEach
    void setUp() {
        List<InstructorClientResponseBody> instructors = List.of(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());
        BDDMockito.when(instructorServiceMock.listAll())
                .thenReturn(instructors);

        BDDMockito.when(instructorServiceMock.returnInstructorClientResponseIfFindById(ArgumentMatchers.anyLong()))
                .thenReturn(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());

        BDDMockito.when(instructorServiceMock.listAllStudentsById(ArgumentMatchers.anyLong()))
                .thenReturn(List.of(InstructorStudentsClientResponseBodyCreator.validInstructorStudentClientResponseBody()));

        BDDMockito.when(instructorServiceMock.save(ArgumentMatchers.any(InstructorPostRequestBody.class)))
                .thenReturn(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());

        BDDMockito.when(instructorServiceMock.replace(ArgumentMatchers.anyLong(), ArgumentMatchers.any(InstructorPutRequestBody.class)))
                .thenReturn(InstructorClientResponseBodyCreator.validUpdatedInstructorClientResponseBody());

        BDDMockito.doNothing().when(instructorServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns list of instructors when successful")
    void list_ReturnsListOfInstructors_WhenSuccessful() {
        String expectedName = InstructorClientResponseBodyCreator.validInstructorClientResponseBody().getName();
        List<InstructorClientResponseBody> instructors = instructorController.listAll().getBody();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(instructors.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns empty list when no instructor is found")
    void list_ReturnsEmptyListOfInstructors_WhenNoInstructorIsFound() {
        BDDMockito.when(instructorServiceMock.listAll()).thenReturn(Collections.emptyList());
        List<InstructorClientResponseBody> instructors = instructorController.listAll().getBody();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns instructor when successful")
    void findById_ReturnsInstructor_WhenSuccessful() {
        InstructorClientResponseBody instructor = InstructorClientResponseBodyCreator.validInstructorClientResponseBody();
        InstructorClientResponseBody foundedInstructor = instructorController.findById(1L).getBody();

        Assertions.assertThat(foundedInstructor)
                .isNotNull()
                .isEqualTo(instructor);
    }

    @Test
    @DisplayName("listAllStudentsById returns list of students of a given instructor when successful")
    void listAllStudentsById_ReturnsListOfStudents_WhenSuccessful() {
        Instructor instructor = InstructorCreator.validInstructor();
        List<InstructorStudentClientResponseBody> students = instructorController.listAllStudentsById(instructor.getId()).getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("listAllStudentsById returns empty list of students of a given instructor when no student is found")
    void listAllStudentsById_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        BDDMockito.when(instructorServiceMock.listAllStudentsById(1L)).thenReturn(Collections.emptyList());
        Instructor instructor = InstructorCreator.validInstructor();
        List<InstructorStudentClientResponseBody> students = instructorController.listAllStudentsById(instructor.getId()).getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns instructor when successful")
    void save_ReturnsInstructor_WhenSuccessful() {
        InstructorClientResponseBody instructor = instructorController.save(InstructorPostRequestBodyCreator.validInstructorPostRequestBody()).getBody();

        Assertions.assertThat(instructor)
                .isNotNull()
                .isEqualTo(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());
    }

    @Test
    @DisplayName("replace updates instructor when successful")
    void replace_UpdatesInstructor_WhenSuccessful() {
        InstructorPutRequestBody instructorPutRequestBody = InstructorPutRequestBodyCreator.validInstructorPutRequestBody();
        InstructorClientResponseBody updatedInstructor = instructorController.replace(1L, instructorPutRequestBody).getBody();

        Assertions.assertThatCode(() -> instructorController.replace(1L, instructorPutRequestBody))
                .doesNotThrowAnyException();
        Assertions.assertThat(updatedInstructor)
                .isNotNull()
                .isEqualTo(InstructorClientResponseBodyCreator.validUpdatedInstructorClientResponseBody());
    }

    @Test
    @DisplayName("delete removes instructor when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        Assertions.assertThatCode(() -> instructorController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> delete = instructorController.delete(1L);
        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

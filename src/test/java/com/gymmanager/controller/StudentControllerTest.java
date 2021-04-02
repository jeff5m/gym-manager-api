package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.service.StudentService;
import com.gymmanager.util.student.StudentClientResponseBodyCreator;
import com.gymmanager.util.student.StudentPostRequestBodyCreator;
import com.gymmanager.util.student.StudentPutRequestBodyCreator;
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
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentServiceMock;

    @BeforeEach
    void setUp() {
        List<StudentClientResponseBody> students = List.of(StudentClientResponseBodyCreator.validStudentClientResponseBody());
        BDDMockito.when(studentServiceMock.listAll())
                .thenReturn(students);

        BDDMockito.when(studentServiceMock.save(ArgumentMatchers.any(StudentPostRequestBody.class)))
                .thenReturn(StudentClientResponseBodyCreator.validStudentClientResponseBody());

        BDDMockito.when(studentServiceMock.replace(ArgumentMatchers.anyLong(), ArgumentMatchers.any(StudentPutRequestBody.class)))
                .thenReturn(StudentClientResponseBodyCreator.validUpdatedStudentClientResponseBody());

        BDDMockito.doNothing().when(studentServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll returns list of students when successful")
    void list_ReturnsListOfStudents_WhenSuccessful() {
        String expectedName = StudentClientResponseBodyCreator.validStudentClientResponseBody().getName();
        List<StudentClientResponseBody> students = studentController.listAll().getBody();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(students.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns empty list of students when no student is found")
    void list_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        BDDMockito.when(studentServiceMock.listAll()).thenReturn(Collections.emptyList());
        List<StudentClientResponseBody> students = studentController.listAll().getBody();
        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns student when successful")
    void save_ReturnsStudent_WhenSuccessful() {
        StudentClientResponseBody savedStudent = studentController.save(StudentPostRequestBodyCreator.validStudentPostRequestBody()).getBody();

        Assertions.assertThat(savedStudent)
                .isNotNull()
                .isEqualTo(StudentClientResponseBodyCreator.validStudentClientResponseBody());
    }

    @Test
    @DisplayName("replace updates student when successful")
    void replace_UpdatesStudent_WhenSuccessful() {
        StudentPutRequestBody studentPutRequestBody = StudentPutRequestBodyCreator.validStudentPutRequestBody();
        StudentClientResponseBody updatedStudent = studentController.replace(1L, studentPutRequestBody).getBody();

        Assertions.assertThat(updatedStudent)
                .isNotNull()
                .isEqualTo(StudentClientResponseBodyCreator.validUpdatedStudentClientResponseBody());
    }

    @Test
    @DisplayName("delete removes student when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        Assertions.assertThatCode(() -> studentController.delete(1L)).doesNotThrowAnyException();

        ResponseEntity<Void> delete = studentController.delete(1L);
        Assertions.assertThat(delete).isNotNull();
        Assertions.assertThat(delete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}

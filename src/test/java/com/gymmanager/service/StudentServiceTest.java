package com.gymmanager.service;

import com.gymmanager.domain.mapper.StudentMapper;
import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.repository.StudentRepository;
import com.gymmanager.util.student.StudentClientResponseBodyCreator;
import com.gymmanager.util.student.StudentCreator;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapperMock;

    @Mock
    private StudentRepository studentRepositoryMock;

    @BeforeEach
    void setUp() {
        List<Student> students = List.of(StudentCreator.validStudent());
        BDDMockito.when(studentRepositoryMock.findAll())
                .thenReturn(students);

        BDDMockito.when(studentMapperMock.toListOfStudentClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(List.of(StudentClientResponseBodyCreator.validStudentClientResponseBody()));

        BDDMockito.when(studentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(StudentCreator.validStudent()));

        BDDMockito.when(studentRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(StudentCreator.validStudent()));

        BDDMockito.when(studentRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(StudentCreator.validStudent()));

        BDDMockito.when(studentRepositoryMock.save(any(Student.class)))
                .thenReturn(StudentCreator.validStudent());

        BDDMockito.when(studentMapperMock.toStudent(ArgumentMatchers.any(StudentPostRequestBody.class)))
                .thenReturn(StudentCreator.validStudentToBeSaved());

        BDDMockito.when(studentMapperMock.toStudent(ArgumentMatchers.any(StudentPutRequestBody.class), ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentCreator.validStudent());

        BDDMockito.when(studentMapperMock.toStudentClientResponseBody(ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentClientResponseBodyCreator.validStudentClientResponseBody());

        BDDMockito.doNothing().when(studentRepositoryMock).delete(any(Student.class));
    }

    @Test
    @DisplayName("listAll returns list of students when successful")
    void listAll_ReturnsListOfStudents_WhenSuccessful() {
        String expectedName = StudentClientResponseBodyCreator.validStudentClientResponseBody().getName();
        List<StudentClientResponseBody> students = studentService.listAll();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(students.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns empty list of students when no student is found")
    void listAll_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        BDDMockito.when(studentMapperMock.toListOfStudentClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(Collections.emptyList());
        List<StudentClientResponseBody> students = studentService.listAll();
        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("findById returns student when successful")
    void findById_ReturnsStudent_WhenSuccessful() {
        Student expectedStudent = StudentCreator.validStudent();
        Student foundedStudent = studentService.findByIdOrThrowNotFoundException(expectedStudent.getId());

        Assertions.assertThat(foundedStudent)
                .isNotNull()
                .isEqualTo(expectedStudent);
    }

    @Test
    @DisplayName("findById throws NotFoundException when no student is found")
    void findById_ThrowsNotFoundException_WhenNoStudentIsFound() {
        BDDMockito.when(studentRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> studentService.findByIdOrThrowNotFoundException(1L));
    }

    @Test
    @DisplayName("save returns student when successful")
    void save_ReturnsStudent_WhenSuccessful() {
        StudentClientResponseBody savedStudent = studentService.save(StudentPostRequestBodyCreator.validStudentPostRequestBody());

        Assertions.assertThat(savedStudent)
                .isNotNull()
                .isEqualTo(StudentClientResponseBodyCreator.validStudentClientResponseBody());
    }

    @Test
    @DisplayName("replace updates student when successful")
    void replace_UpdatesStudent_WhenSuccessful() {
        BDDMockito.when(studentMapperMock.toStudentClientResponseBody(ArgumentMatchers.any(Student.class)))
                .thenReturn(StudentClientResponseBodyCreator.validUpdatedStudentClientResponseBody());

        StudentPutRequestBody studentPutRequestBody = StudentPutRequestBodyCreator.validStudentPutRequestBody();
        StudentClientResponseBody updatedStudent = studentService.replace(1L, studentPutRequestBody);

        Assertions.assertThat(updatedStudent)
                .isNotNull()
                .isEqualTo(StudentClientResponseBodyCreator.validUpdatedStudentClientResponseBody());
    }

    @Test
    @DisplayName("delete removes student when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        Assertions.assertThatCode(() -> studentService.delete(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findByEmail returns student when successful")
    void findByEmail_ReturnsStudent_WhenSuccessful() {
        Student expectedStudent = StudentCreator.validStudent();
        Optional<Student> foundedStudent = studentService.findByEmail(expectedStudent.getEmail());

        Assertions.assertThat(foundedStudent)
                .isNotNull()
                .isPresent()
                .isEqualTo(Optional.of(expectedStudent));
    }

    @Test
    @DisplayName("findByEmail returns empty optional when no student is found")
    void findByEmail_ReturnsEmptyOptional_WhenNoStudentIsFound() {
        BDDMockito.when(studentRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Optional<Student> expectedStudent = studentService.findByEmail("1234@email.com");

        Assertions.assertThat(expectedStudent)
                .isNotPresent();
    }

    @Test
    @DisplayName("findByCpf returns student when successful")
    void findByCpf_ReturnsStudent_WhenSuccessful() {
        Student expectedStudent = StudentCreator.validStudent();
        Optional<Student> foundedStudent = studentService.findByCpf(expectedStudent.getCpf());

        Assertions.assertThat(foundedStudent)
                .isNotNull()
                .isPresent()
                .isEqualTo(Optional.of(expectedStudent));
    }

    @Test
    @DisplayName("findByCpf returns empty optional when no student is found")
    void findByCpf_ReturnsEmptyOptional_WhenNoStudentIsFound() {
        BDDMockito.when(studentRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Optional<Student> expectedStudent = studentService.findByCpf("00000000000");

        Assertions.assertThat(expectedStudent)
                .isNotPresent();
    }
}

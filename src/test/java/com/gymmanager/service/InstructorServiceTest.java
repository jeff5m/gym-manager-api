package com.gymmanager.service;

import com.gymmanager.domain.mapper.InstructorMapper;
import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.repository.InstructorRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class InstructorServiceTest {

    @InjectMocks
    private InstructorService instructorService;

    @Mock
    private InstructorMapper instructorMapperMock;

    @Mock
    private InstructorRepository instructorRepositoryMock;

    @BeforeEach
    void setUp() {
        List<Instructor> instructors = List.of(InstructorCreator.validInstructor());
        BDDMockito.when(instructorRepositoryMock.findAll())
                .thenReturn(instructors);

        BDDMockito.when(instructorMapperMock.toListOfInstructorClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(List.of(InstructorClientResponseBodyCreator.validInstructorClientResponseBody()));

        BDDMockito.when(instructorMapperMock.toInstructorClientResponseBody(ArgumentMatchers.any(Instructor.class)))
                .thenReturn(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());

        BDDMockito.when(instructorRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(InstructorCreator.validInstructor()));

        BDDMockito.when(instructorMapperMock.toListOfInstructorsStudentsClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(List.of(InstructorStudentsClientResponseBodyCreator.validInstructorStudentClientResponseBody()));

        BDDMockito.when(instructorRepositoryMock.save(ArgumentMatchers.any(Instructor.class)))
                .thenReturn(InstructorCreator.validInstructor());

        BDDMockito.when(instructorMapperMock.toInstructor(ArgumentMatchers.any(InstructorPostRequestBody.class), ArgumentMatchers.any(LocalDate.class)))
                .thenReturn(InstructorCreator.validInstructor());

        BDDMockito.when(instructorMapperMock.toInstructorClientResponseBody(ArgumentMatchers.any(Instructor.class)))
                .thenReturn(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());

        BDDMockito.when(instructorMapperMock.toInstructor(ArgumentMatchers.any(InstructorPutRequestBody.class), ArgumentMatchers.any(Instructor.class)))
                .thenReturn(InstructorCreator.validInstructor());

        BDDMockito.when(instructorRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(InstructorCreator.validInstructor()));

        BDDMockito.when(instructorRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(InstructorCreator.validInstructor()));
    }

    @Test
    @DisplayName("listAll returns list of instructors when successful")
    void list_ReturnsListOfInstructors_WhenSuccessful() {
        String expectedName = InstructorClientResponseBodyCreator.validInstructorClientResponseBody().getName();
        List<InstructorClientResponseBody> instructors = instructorService.listAll();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(instructors.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("listAll returns empty list when no instructor is found")
    void list_ReturnsEmptyListOfInstructors_WhenNoInstructorIsFound() {
        BDDMockito.when(instructorMapperMock.toListOfInstructorClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(Collections.emptyList());
        List<InstructorClientResponseBody> instructors = instructorService.listAll();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("returnInstructorClientResponseIfFindById returns instructor when successful")
    void returnInstructorClientResponseIfFindById_ReturnsInstructor_WhenSuccessful() {
        InstructorClientResponseBody expectedInstructor = InstructorClientResponseBodyCreator.validInstructorClientResponseBody();
        InstructorClientResponseBody foundedInstructor = instructorService.returnInstructorClientResponseIfFindById(expectedInstructor.getId());

        Assertions.assertThat(foundedInstructor)
                .isNotNull()
                .isEqualTo(expectedInstructor);
    }

    @Test
    @DisplayName("listAllStudentsById returns list of students of a given instructor when successful")
    void listAllStudentsById_ReturnsListOfStudents_WhenSuccessful() {
        Instructor instructor = InstructorCreator.validInstructor();
        List<InstructorStudentClientResponseBody> students = instructorService.listAllStudentsById(instructor.getId());

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    @DisplayName("listAllStudentsById returns empty list of students of a given instructor when no student is found")
    void listAllStudentsById_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        BDDMockito.when(instructorMapperMock.toListOfInstructorsStudentsClientResponseBody(ArgumentMatchers.anyList()))
                .thenReturn(Collections.emptyList());
        Instructor instructor = InstructorCreator.validInstructor();
        List<InstructorStudentClientResponseBody> students =
                instructorService.listAllStudentsById(instructor.getId());

        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("save returns instructor when successful")
    void save_ReturnsInstructor_WhenSuccessful() {
        InstructorClientResponseBody savedInstructor =
                instructorService.save(InstructorPostRequestBodyCreator.validInstructorPostRequestBody());

        Assertions.assertThat(savedInstructor)
                .isNotNull()
                .isEqualTo(InstructorClientResponseBodyCreator.validInstructorClientResponseBody());
    }

    @Test
    @DisplayName("replace updates instructor when successful")
    void replace_UpdatesInstructor_WhenSuccessful() {
        InstructorPutRequestBody instructorPutRequestBody = InstructorPutRequestBodyCreator.validInstructorPutRequestBody();
        InstructorClientResponseBody updatedInstructor = instructorService.replace(1L, instructorPutRequestBody);
        updatedInstructor.setAvatarUrl(instructorPutRequestBody.getAvatarUrl());

        Assertions.assertThat(updatedInstructor)
                .isNotNull()
                .isEqualTo(InstructorClientResponseBodyCreator.validUpdatedInstructorClientResponseBody());
    }

    @Test
    @DisplayName("delete removes instructor when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        Assertions.assertThatCode(() -> instructorService.delete(1L))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("findById returns instructor when successful")
    void findById_ReturnsInstructor_WhenSuccessful() {
        Instructor expectedInstructor = InstructorCreator.validInstructor();
        Instructor foundedInstructor = instructorService.findByIdOrThrowNotFoundException(expectedInstructor.getId());

        Assertions.assertThat(foundedInstructor)
                .isNotNull()
                .isEqualTo(expectedInstructor);
    }

    @Test
    @DisplayName("findById throws NotFoundException when no instructor is found")
    void findById_ThrowsNotFoundException_WhenNoStudentIsFound() {
        BDDMockito.when(instructorRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> instructorService.findByIdOrThrowNotFoundException(1L));
    }

    @Test
    @DisplayName("findByEmail returns instructor when successful")
    void findByEmail_ReturnsInstructor_WhenSuccessful() {
        Instructor expectedInstructor = InstructorCreator.validInstructor();
        Optional<Instructor> foundedInstructor = instructorService.findByEmail(expectedInstructor.getEmail());

        Assertions.assertThat(foundedInstructor)
                .isNotNull()
                .isPresent()
                .isEqualTo(Optional.of(expectedInstructor));
    }

    @Test
    @DisplayName("findByEmail returns empty optional when no instructor is found")
    void findByEmail_ReturnsEmptyOptional_WhenNoStudentIsFound() {
        BDDMockito.when(instructorRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Optional<Instructor> expectedInstructor = instructorService.findByEmail("1234@email.com");

        Assertions.assertThat(expectedInstructor)
                .isNotPresent();
    }

    @Test
    @DisplayName("findByCpf returns instructor when successful")
    void findByCpf_ReturnsInstructor_WhenSuccessful() {
        Instructor expectedInstructor = InstructorCreator.validInstructor();
        Optional<Instructor> foundedInstructor = instructorService.findByCpf(expectedInstructor.getCpf());

        Assertions.assertThat(foundedInstructor)
                .isNotNull()
                .isPresent()
                .isEqualTo(Optional.of(expectedInstructor));
    }

    @Test
    @DisplayName("findByCpf returns empty optional when no instructor is found")
    void findByCpf_ReturnsEmptyOptional_WhenNoStudentIsFound() {
        BDDMockito.when(instructorRepositoryMock.findByCpf(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        Optional<Instructor> expectedInstructor = instructorService.findByCpf("00000000000");

        Assertions.assertThat(expectedInstructor)
                .isNotPresent();
    }
}

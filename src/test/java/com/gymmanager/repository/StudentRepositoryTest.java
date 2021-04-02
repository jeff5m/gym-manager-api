package com.gymmanager.repository;

import com.gymmanager.domain.model.Student;
import com.gymmanager.util.instructor.InstructorCreator;
import com.gymmanager.util.student.StudentCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InstructorRepository instructorRepository;

    @BeforeEach
    public void init() {
        instructorRepository.save(InstructorCreator.validInstructor());
    }

    @Test
    @DisplayName("List All return list of all students when successful")
    void listAll_ReturnsListOfStudents_WhenSuccessful() {
        assert instructorRepository.findById(1L).isPresent();

        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor(instructorRepository.findById(1L).get());
        this.studentRepository.save(studentToBeSaved);

        List<Student> students = this.studentRepository.findAll();

        Assertions.assertThat(students)
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    @DisplayName("List All return empty list when no student is found")
    void listAll_ReturnsEmptyListOfStudents_WhenNoStudentIsFound() {
        List<Student> students = this.studentRepository.findAll();

        Assertions.assertThat(students)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save persists student when successful")
    void save_PersistsStudent_WhenSuccessful() {
        assert instructorRepository.findById(1L).isPresent();

        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor((instructorRepository.findById(1L).get()));
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        Assertions.assertThat(studentSaved).isNotNull();
        Assertions.assertThat(studentSaved.getId()).isNotNull();
        Assertions.assertThat(studentSaved.getName()).isEqualTo(studentToBeSaved.getName());
        Assertions.assertThat(studentSaved.getAvatarUrl()).isEqualTo(studentToBeSaved.getAvatarUrl());
        Assertions.assertThat(studentSaved.getEmail()).isEqualTo(studentToBeSaved.getEmail());
        Assertions.assertThat(studentSaved.getCpf()).isEqualTo(studentToBeSaved.getCpf());
        Assertions.assertThat(studentSaved.getWeight()).isEqualTo(studentToBeSaved.getWeight());
        Assertions.assertThat(studentSaved.getHeight()).isEqualTo(studentToBeSaved.getHeight());
        Assertions.assertThat(studentSaved.getAge()).isEqualTo(studentToBeSaved.getAge());
        Assertions.assertThat(studentSaved.getGender()).isEqualTo(studentToBeSaved.getGender());
        Assertions.assertThat(studentSaved.getInstructor()).isEqualTo(studentToBeSaved.getInstructor());
    }

    @Test
    @DisplayName("Check exists student by email return true if exist")
    void check_ExistsStudentByEmail_IfExists() {
        assert instructorRepository.findById(1L).isPresent();
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor((instructorRepository.findById(1L).get()));
        Student studentSaved = this.studentRepository.save(studentToBeSaved);
        assert studentRepository.findByEmail(studentSaved.getEmail()).isPresent();
        String email = studentRepository.findByEmail(studentSaved.getEmail()).get().getEmail();

        Assertions.assertThat(studentRepository.findByEmail(email)).isPresent();
    }

    @Test
    @DisplayName("Check exists student by email return true if does not exist")
    void check_ExistsStudentByEmail_IfDoesNotExists() {
        Optional<Student> studentOptional = studentRepository.findByEmail("test@email.com");

        Assertions.assertThat(studentOptional).isEmpty();
    }

    @Test
    @DisplayName("Check exists student by cpf return true if exist")
    void check_ExistsStudentByCpf_IfExists() {
        assert instructorRepository.findById(1L).isPresent();
        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor((instructorRepository.findById(1L).get()));
        Student studentSaved = this.studentRepository.save(studentToBeSaved);
        assert studentRepository.findByCpf(studentSaved.getCpf()).isPresent();
        String cpf = studentRepository.findByCpf(studentSaved.getCpf()).get().getCpf();

        Assertions.assertThat(studentRepository.findByCpf(cpf)).isPresent();
    }

    @Test
    @DisplayName("Check exists student by cpf return true if does not exist")
    void check_ExistsStudentByCpf_IfDoesNotExists() {
        Optional<Student> studentOptional = studentRepository.findByCpf("95611559239");

        Assertions.assertThat(studentOptional).isEmpty();
    }

    @Test
    @DisplayName("Save updates student when successful")
    void save_UpdatesStudent_WhenSuccessful() {
        assert instructorRepository.findById(1L).isPresent();

        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor((instructorRepository.findById(1L).get()));
        Student studentSaved = this.studentRepository.save(studentToBeSaved);
        studentSaved.setName("Jane Doe Student Updated");
        Student studentUpdated = this.studentRepository.save(studentSaved);

        Assertions.assertThat(studentUpdated).isNotNull();
        Assertions.assertThat(studentUpdated.getId()).isNotNull();
        Assertions.assertThat(studentUpdated.getName()).isEqualTo(studentSaved.getName());
        Assertions.assertThat(studentUpdated.getAvatarUrl()).isEqualTo(studentToBeSaved.getAvatarUrl());
        Assertions.assertThat(studentUpdated.getEmail()).isEqualTo(studentToBeSaved.getEmail());
        Assertions.assertThat(studentUpdated.getCpf()).isEqualTo(studentToBeSaved.getCpf());
        Assertions.assertThat(studentUpdated.getWeight()).isEqualTo(studentToBeSaved.getWeight());
        Assertions.assertThat(studentUpdated.getHeight()).isEqualTo(studentToBeSaved.getHeight());
        Assertions.assertThat(studentUpdated.getAge()).isEqualTo(studentToBeSaved.getAge());
        Assertions.assertThat(studentUpdated.getGender()).isEqualTo(studentToBeSaved.getGender());
        Assertions.assertThat(studentUpdated.getInstructor()).isEqualTo(studentToBeSaved.getInstructor());
    }

    @Test
    @DisplayName("Delete removes student when successful")
    void delete_RemovesStudent_WhenSuccessful() {
        assert instructorRepository.findById(1L).isPresent();

        Student studentToBeSaved = StudentCreator.validStudentToBeSaved();
        studentToBeSaved.setInstructor((instructorRepository.findById(1L).get()));
        Student studentSaved = this.studentRepository.save(studentToBeSaved);

        this.studentRepository.delete(studentSaved);
        Optional<Student> studentOptional = this.studentRepository.findById(studentSaved.getId());

        Assertions.assertThat(studentOptional).isEmpty();
    }
}

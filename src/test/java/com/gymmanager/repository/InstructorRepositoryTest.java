package com.gymmanager.repository;

import com.gymmanager.domain.model.Instructor;
import com.gymmanager.util.instructor.InstructorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    @DisplayName("List All returns list of all instructors when successful")
    void listAll_ReturnsListOfInstructors_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        this.instructorRepository.save(instructorToBeSaved);
        List<Instructor> instructors = this.instructorRepository.findAll();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(instructors.get(0)).isEqualTo(instructorToBeSaved);
    }

    @Test
    @DisplayName("List All returns empty list when no instructor is found")
    void listAll_ReturnsEmptyListOfInstructors_WhenNoInstructorIsFound() {
        List<Instructor> instructors = this.instructorRepository.findAll();

        Assertions.assertThat(instructors)
                .isNotNull()
                .isEmpty();
    }

    @Test
    @DisplayName("Save persists instructor when successful")
    void save_PersistsInstructor_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = this.instructorRepository.save(instructorToBeSaved);

        Assertions.assertThat(instructorSaved).isNotNull();
        Assertions.assertThat(instructorSaved.getName()).isEqualTo(instructorToBeSaved.getName());
        Assertions.assertThat(instructorSaved.getAvatarUrl()).isEqualTo(instructorToBeSaved.getAvatarUrl());
        Assertions.assertThat(instructorSaved.getEmail()).isEqualTo(instructorToBeSaved.getEmail());
        Assertions.assertThat(instructorSaved.getCpf()).isEqualTo(instructorToBeSaved.getCpf());
        Assertions.assertThat(instructorSaved.getServices()).isEqualTo(instructorToBeSaved.getServices());
        Assertions.assertThat(instructorSaved.getStudents()).isEqualTo(instructorToBeSaved.getStudents());
        Assertions.assertThat(instructorSaved.getBirth()).isEqualTo(instructorToBeSaved.getBirth());
        Assertions.assertThat(instructorSaved.getCreatedAt()).isEqualTo(instructorToBeSaved.getCreatedAt());
    }

    @Test
    @DisplayName("Check exists instructor by email return true if exist")
    void check_ExistsInstructorByEmail_IfExists() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = this.instructorRepository.save(instructorToBeSaved);
        String email = instructorToBeSaved.getEmail();

        Assertions.assertThat(instructorToBeSaved.getEmail()).isEqualTo(instructorSaved.getEmail());
        Assertions.assertThat(this.instructorRepository.findByEmail(email)).isPresent();
    }

    @Test
    @DisplayName("Check exists instructor by email return true if does not exist")
    void check_ExistsInstructorByEmail_IfDoesNotExists() {
        Optional<Instructor> instructorOptional = this.instructorRepository.findByEmail("asdf@email.com");

        Assertions.assertThat(instructorOptional).isEmpty();
    }

    @Test
    @DisplayName("Check exists instructor by cpf return true if exist")
    void check_ExistsInstructorByCpf_IfExists() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = this.instructorRepository.save(instructorToBeSaved);
        String cpf = instructorToBeSaved.getCpf();

        Assertions.assertThat(instructorToBeSaved.getCpf()).isEqualTo(instructorSaved.getCpf());
        Assertions.assertThat(this.instructorRepository.findByCpf(cpf)).isPresent();
    }

    @Test
    @DisplayName("Check exists instructor by cpf return true if does not exist")
    void check_ExistsInstructorByCpf_IfDoesNotExists() {
        Optional<Instructor> instructorOptional = this.instructorRepository.findByCpf("00000000000");

        Assertions.assertThat(instructorOptional).isEmpty();
    }

    @Test
    @DisplayName("Save updates instructor when successful")
    void save_UpdatesInstructor_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = this.instructorRepository.save(instructorToBeSaved);
        instructorSaved.setName("Jane Doe Instructor Updated");
        Instructor instructorUpdated = this.instructorRepository.save(instructorToBeSaved);

        Assertions.assertThat(instructorUpdated).isNotNull();
        Assertions.assertThat(instructorUpdated.getId()).isNotNull();
        Assertions.assertThat(instructorUpdated.getName()).isEqualTo(instructorSaved.getName());
        Assertions.assertThat(instructorUpdated.getAvatarUrl()).isEqualTo(instructorSaved.getAvatarUrl());
        Assertions.assertThat(instructorUpdated.getEmail()).isEqualTo(instructorSaved.getEmail());
        Assertions.assertThat(instructorUpdated.getCpf()).isEqualTo(instructorSaved.getCpf());
        Assertions.assertThat(instructorUpdated.getServices()).isEqualTo(instructorSaved.getServices());
        Assertions.assertThat(instructorUpdated.getStudents()).isEqualTo(instructorSaved.getStudents());
        Assertions.assertThat(instructorUpdated.getBirth()).isEqualTo(instructorSaved.getBirth());
        Assertions.assertThat(instructorUpdated.getCreatedAt()).isEqualTo(instructorSaved.getCreatedAt());
    }

    @Test
    @DisplayName("Delete removes instructor when successful")
    void delete_RemovesInstructor_WhenSuccessful() {
        Instructor instructorToBeSaved = InstructorCreator.validInstructorToBeSaved();
        Instructor instructorSaved = this.instructorRepository.save(instructorToBeSaved);
        this.instructorRepository.delete(instructorSaved);
        Optional<Instructor> instructorOptional = this.instructorRepository.findById(instructorSaved.getId());

        Assertions.assertThat(instructorOptional).isEmpty();
    }
}
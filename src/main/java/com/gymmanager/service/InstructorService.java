package com.gymmanager.service;

import com.gymmanager.domain.mapper.InstructorMapper;
import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;

    public List<InstructorClientResponseBody> listAll() {
        return instructorMapper.toListOfInstructorClientResponseBody(instructorRepository.findAll());
    }

    public InstructorClientResponseBody returnInstructorClientResponseIfFindById(Long id) {
        Instructor foundedInstructor = findByIdOrThrowNotFoundException(id);
        return instructorMapper.toInstructorClientResponseBody(foundedInstructor);
    }

    public List<InstructorStudentClientResponseBody> listAllStudentsById(Long id) {
        Instructor instructor = findByIdOrThrowNotFoundException(id);
        return instructorMapper.toListOfInstructorsStudentsClientResponseBody(instructor.getStudents());
    }

    @Transactional
    public InstructorClientResponseBody save(@RequestBody @Valid InstructorPostRequestBody instructorPostRequestBody) {
        LocalDate creationDate = LocalDate.now();
        Instructor instructor = instructorMapper.toInstructor(instructorPostRequestBody, creationDate);

        return instructorMapper.toInstructorClientResponseBody(instructorRepository.save(instructor));
    }

    @Transactional
    public InstructorClientResponseBody replace(Long id, InstructorPutRequestBody instructorPutRequestBody) {
        Instructor savedInstructor = findByIdOrThrowNotFoundException(id);
        Instructor updatedInstructor = instructorMapper.toInstructor(instructorPutRequestBody, savedInstructor);

        return instructorMapper.toInstructorClientResponseBody(instructorRepository.save(updatedInstructor));
    }

    @Transactional
    public void delete(Long id) {
        instructorRepository.delete(findByIdOrThrowNotFoundException(id));
    }

    public Optional<Instructor> findByCpf(String cpf) {
        return instructorRepository.findByCpf(cpf);
    }

    public Optional<Instructor> findByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    public Instructor findAndReturnInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    private Instructor findByIdOrThrowNotFoundException(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }
}

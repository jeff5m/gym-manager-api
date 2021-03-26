package com.gymmanager.service;

import com.gymmanager.domain.mapper.InstructorMapper;
import com.gymmanager.domain.mapper.requests.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.InstructorPutRequestBody;
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

    private Instructor findByIdOrThrowNotFoundException(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }
}

package com.gymmanager.domain.service;

import com.gymmanager.api.exptionhandler.exceptions.CpfAlreadyRegisteredException;
import com.gymmanager.api.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.api.mapper.InstructorMapper;
import com.gymmanager.api.mapper.requests.InstructorClientResponseBody;
import com.gymmanager.api.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.api.mapper.requests.InstructorPutRequestBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.repository.InstructorRepository;
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

    @Transactional
    public InstructorClientResponseBody save(@RequestBody @Valid InstructorPostRequestBody instructorPostRequestBody) {
        
        Optional<Instructor> existentInstructor = instructorRepository.findByCpf(instructorPostRequestBody.getCpf());
        if (existentInstructor.isPresent() && existentInstructor.get().getCpf().equals(instructorPostRequestBody.getCpf())) {
            throw new CpfAlreadyRegisteredException("There is already a registered Instructor with this cpf");
        }
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

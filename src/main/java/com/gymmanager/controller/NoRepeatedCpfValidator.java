package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.repository.InstructorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoRepeatedCpfValidator extends UniqueFieldValidator {

    private final InstructorRepository instructorRepository;

    public NoRepeatedCpfValidator(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Optional<Instructor> findInstructorBySomeField(InstructorPostRequestBody instructorPostRequestBody) {
        return instructorRepository.findByCpf(instructorPostRequestBody.getCpf());
    }

    @Override
    protected String getInvalidFieldName() {
        return "cpf";
    }
}

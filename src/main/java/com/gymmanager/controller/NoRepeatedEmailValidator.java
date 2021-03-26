package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.repository.InstructorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoRepeatedEmailValidator extends UniqueFieldValidator {

    private final InstructorRepository instructorRepository;

    public NoRepeatedEmailValidator(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Optional<Instructor> findInstructorBySomeField(InstructorPostRequestBody instructorPostRequestBody) {
        return instructorRepository.findByEmail(instructorPostRequestBody.getEmail());
    }

    @Override
    protected String getInvalidFieldName() {
        return "email";
    }
}

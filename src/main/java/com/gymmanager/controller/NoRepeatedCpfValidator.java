package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class NoRepeatedCpfValidator implements Validator {

    private final InstructorRepository instructorRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return InstructorPostRequestBody.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        InstructorPostRequestBody instructorPostRequestBody = (InstructorPostRequestBody) o;
        Optional<Instructor> possibleInstructor = instructorRepository.findByCpf(instructorPostRequestBody.getCpf());

        if (possibleInstructor.isPresent()) {
            errors.rejectValue("cpf", null, "Já existe um instrutor com o cpf informado");
        }
    }
}

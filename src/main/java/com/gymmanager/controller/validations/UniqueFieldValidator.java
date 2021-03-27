package com.gymmanager.controller.validations;

import com.gymmanager.service.InstructorService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator implements ConstraintValidator<Unique, String> {

    private final InstructorService instructorService;

    public UniqueFieldValidator(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if(value.contains("@")) {
            return instructorService.findByEmail(value).isEmpty();
        }

        return instructorService.findByCpf(value).isEmpty();
    }
}

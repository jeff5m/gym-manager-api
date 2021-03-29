package com.gymmanager.controller.validations;

import com.gymmanager.domain.model.Instructor;
import com.gymmanager.service.InstructorService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueFieldValidator implements ConstraintValidator<Unique, String> {

    private final InstructorService instructorService;

    public UniqueFieldValidator(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.contains("@")) {
            String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            String id = uri.substring(uri.lastIndexOf('/') + 1);
            Optional<Instructor> foundedInstructor = instructorService.findByEmail(value);
            if (isNumeric(id) && foundedInstructor.isPresent()) {
                return foundedInstructor.get().getId().equals(Long.parseLong(id));
            }
            return instructorService.findByEmail(value).isEmpty();
        }

        return instructorService.findByCpf(value).isEmpty();
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}

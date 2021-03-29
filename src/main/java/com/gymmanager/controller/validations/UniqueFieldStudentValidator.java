package com.gymmanager.controller.validations;

import com.gymmanager.domain.model.Student;
import com.gymmanager.service.StudentService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueFieldStudentValidator implements ConstraintValidator<UniqueFieldStudent, String> {

    private final StudentService studentService;

    public UniqueFieldStudentValidator(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null && value.contains("@")) {
            String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            String id = uri.substring(uri.lastIndexOf('/') + 1);
            Optional<Student> foundedStudent = studentService.findByEmail(value);
            if (isNumeric(id) && foundedStudent.isPresent()) {
                return foundedStudent.get().getId().equals(Long.parseLong(id));
            }
            return studentService.findByEmail(value).isEmpty();
        }

        return studentService.findByCpf(value).isEmpty();
    }

    private boolean isNumeric(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}

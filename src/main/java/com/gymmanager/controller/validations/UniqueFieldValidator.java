package com.gymmanager.controller.validations;

import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.model.Instructor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

public abstract class UniqueFieldValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return InstructorPostRequestBody.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        InstructorPostRequestBody instructorPostRequestBody = (InstructorPostRequestBody) o;
        Optional<Instructor> possibleInstructor = findInstructorBySomeField(instructorPostRequestBody);

        String invalidFieldName = getInvalidFieldName();
        if (possibleInstructor.isPresent()) {
            errors.rejectValue(invalidFieldName,"","There is already an instructor registered with this " + invalidFieldName);
        }
    }

    public abstract Optional<Instructor> findInstructorBySomeField(InstructorPostRequestBody instructorPostRequestBody);

    protected abstract String getInvalidFieldName();
}

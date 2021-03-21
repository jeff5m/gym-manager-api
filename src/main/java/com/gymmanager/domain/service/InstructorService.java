package com.gymmanager.domain.service;

import com.gymmanager.api.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.repository.InstructorRepository;
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

    public List<Instructor> listAll() {
        return instructorRepository.findAll();
    }

    @Transactional
    public Instructor findByIdOrThrowNotFoundException(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    @Transactional
    public Instructor save(@RequestBody @Valid Instructor instructor) {
        instructor.setCreatedAt(LocalDate.now());
        return instructorRepository.save(instructor);
    }

    @Transactional
    public Instructor replace(Instructor updatedInstructor) {
        Instructor savedInstructor = findByIdOrThrowNotFoundException(updatedInstructor.getId());
        updatedInstructor.setCreatedAt(savedInstructor.getCreatedAt());
        updatedInstructor.setId(savedInstructor.getId());

        return instructorRepository.save(updatedInstructor);
    }
}

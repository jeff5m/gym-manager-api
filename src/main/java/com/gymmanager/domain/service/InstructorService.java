package com.gymmanager.domain.service;

import com.gymmanager.api.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public List<Instructor> listAll() {
        return instructorRepository.findAll();
    }

    public Instructor save(Instructor instructor) {
        instructor.setCreatedAt(LocalDate.now());
        return instructorRepository.save(instructor);
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }
}

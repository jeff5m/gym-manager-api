package com.gymmanager.domain.service;

import com.gymmanager.api.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.api.exptionhandler.exceptions.CpfAlreadyRegisteredException;
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

    public List<Instructor> listAll() {
        return instructorRepository.findAll();
    }

    public Instructor findByIdOrThrowNotFoundException(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Instructor not found"));
    }

    @Transactional
    public Instructor save(@RequestBody @Valid Instructor instructor) {
        Optional<Instructor> existentInstructor = instructorRepository.findByCpf(instructor.getCpf());
        if (existentInstructor.isPresent() && !existentInstructor.get().equals(instructor)) {
            throw new CpfAlreadyRegisteredException("There is already a registered instructor with this cpf");
        }
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

    @Transactional
    public void delete(Long id) {
        instructorRepository.delete(findByIdOrThrowNotFoundException(id));
    }
}

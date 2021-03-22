package com.gymmanager.api.controller;

import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> listAll() {
        return new ResponseEntity<>(instructorService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path="/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorService.findByIdOrThrowNotFoundException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Instructor> save(@RequestBody @Valid Instructor instructor) {
        return new ResponseEntity<>(instructorService.save(instructor), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Instructor> replace(@RequestBody @Valid Instructor instructor) {
        return new ResponseEntity<>(instructorService.replace(instructor), HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

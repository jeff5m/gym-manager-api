package com.gymmanager.api.controller;

import com.gymmanager.domain.model.Instructor;
import com.gymmanager.domain.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{instructorId}")
    public ResponseEntity<Instructor> findById(@PathVariable Long instructorId) {
        return new ResponseEntity<>(instructorService.findById(instructorId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Instructor> save(@RequestBody Instructor instructor) {
        return new ResponseEntity<>(instructorService.save(instructor), HttpStatus.CREATED);
    }
}

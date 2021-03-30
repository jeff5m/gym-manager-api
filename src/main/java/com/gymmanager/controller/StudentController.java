package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentClientResponseBody>> listAll() {
        return new ResponseEntity<>(studentService.listAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentClientResponseBody> save(@RequestBody @Valid StudentPostRequestBody student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }
}

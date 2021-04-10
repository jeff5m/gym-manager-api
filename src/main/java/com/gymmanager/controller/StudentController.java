package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lists data for all students", tags = "student")
    @ApiResponse(responseCode = "200",description = "When successful")
    public ResponseEntity<List<StudentClientResponseBody>> listAll() {
        return new ResponseEntity<>(studentService.listAll(), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Persists a new student", tags = "student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "When successful"),
            @ApiResponse(responseCode = "400",description = "When one or more fields are invalid")
    })
    public ResponseEntity<StudentClientResponseBody> save(@RequestBody @Valid StudentPostRequestBody student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Updates an existing student", tags = "student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "When successful"),
            @ApiResponse(responseCode = "400",description = "When one or more fields are invalid"),
            @ApiResponse(responseCode = "404",description = "When no Student is found")
    })
    public ResponseEntity<StudentClientResponseBody> replace(@PathVariable Long id,
                                                             @RequestBody @Valid StudentPutRequestBody student) {
        return new ResponseEntity<>(studentService.replace(id, student), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletes an existing student", tags = "student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "When successful"),
            @ApiResponse(responseCode = "404",description = "When no Student is found")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

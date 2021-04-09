package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorStudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.instructor.InstructorPutRequestBody;
import com.gymmanager.service.InstructorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Lists data for all instructors", tags = "instructor")
    @ApiResponse(responseCode = "200",description = "When successful")
    public ResponseEntity<List<InstructorClientResponseBody>> listAll() {
        return new ResponseEntity<>(instructorService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Lists data for a specific instructor finding by or id", tags = "instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "When successful"),
            @ApiResponse(responseCode = "404",description = "When no Instructor is found"),
    })
    public ResponseEntity<InstructorClientResponseBody> findById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorService.returnInstructorClientResponseIfFindById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/students")
    @Operation(summary = "Lists students for a specific instructor finding by or id", tags = "instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "When successful"),
            @ApiResponse(responseCode = "404",description = "When no Instructor is found"),
    })
    public ResponseEntity<List<InstructorStudentClientResponseBody>> listAllStudentsById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorService.listAllStudentsById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Persists a new instructor", tags = "instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "When successful"),
            @ApiResponse(responseCode = "400",description = "When one or more fields are invalid")
    })
    public ResponseEntity<InstructorClientResponseBody> save(@RequestBody @Valid InstructorPostRequestBody instructorPostRequestBody) {
        return new ResponseEntity<>(instructorService.save(instructorPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Updates an existing instructor", tags = "instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "When successful"),
            @ApiResponse(responseCode = "400",description = "When one or more fields are invalid"),
            @ApiResponse(responseCode = "404",description = "When no Instructor is found")
    })
    public ResponseEntity<InstructorClientResponseBody> replace(@PathVariable Long id,
                                                                @RequestBody @Valid InstructorPutRequestBody instructorPutRequestBody) {
        return new ResponseEntity<>(instructorService.replace(id, instructorPutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletes an existing instructor", tags = "instructor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "When successful"),
            @ApiResponse(responseCode = "404",description = "When no Instructor is found"),
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

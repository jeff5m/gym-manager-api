package com.gymmanager.controller;

import com.gymmanager.domain.mapper.requests.InstructorClientResponseBody;
import com.gymmanager.domain.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.domain.mapper.requests.InstructorPutRequestBody;
import com.gymmanager.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;
    private final NoRepeatedCpfValidator noRepeatedCpfValidator;
    private final NoRepeatedEmailValidator noRepeatedEmailValidator;

    @InitBinder("instructorPostRequestBody")
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(noRepeatedCpfValidator);
        dataBinder.addValidators(noRepeatedEmailValidator);
    }

    @GetMapping
    public ResponseEntity<List<InstructorClientResponseBody>> listAll() {
        return new ResponseEntity<>(instructorService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<InstructorClientResponseBody> findById(@PathVariable Long id) {
        return new ResponseEntity<>(instructorService.returnInstructorClientResponseIfFindById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InstructorClientResponseBody> save(@RequestBody @Valid InstructorPostRequestBody instructorPostRequestBody) {
        return new ResponseEntity<>(instructorService.save(instructorPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<InstructorClientResponseBody> replace(@PathVariable Long id,
                                              @RequestBody @Valid InstructorPutRequestBody instructorPutRequestBody) {
        return new ResponseEntity<>(instructorService.replace(id, instructorPutRequestBody), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        instructorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
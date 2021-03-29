package com.gymmanager.service;

import com.gymmanager.domain.mapper.StudentMapper;
import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public StudentClientResponseBody save(StudentPostRequestBody studentPostRequestBody) {
        Student student = studentMapper.toStudent(studentPostRequestBody, studentPostRequestBody.getInstructor().getId());

        return studentMapper.toStudentClientResponseBody(studentRepository.save(student));
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<Student> findByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }
}

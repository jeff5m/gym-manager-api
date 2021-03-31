package com.gymmanager.service;

import com.gymmanager.domain.mapper.StudentMapper;
import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.exptionhandler.exceptions.NotFoundException;
import com.gymmanager.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public List<StudentClientResponseBody> listAll() {
        return studentMapper.toListOfStudentClientResponseBody(studentRepository.findAll());
    }

    @Transactional
    public StudentClientResponseBody save(StudentPostRequestBody studentPostRequestBody) {
        Student student = studentMapper.toStudent(studentPostRequestBody, studentPostRequestBody.getInstructor().getId());

        return studentMapper.toStudentClientResponseBody(studentRepository.save(student));
    }

    @Transactional
    public StudentClientResponseBody replace(Long id, StudentPutRequestBody student) {
        Student foundedStudent = findByIdOrThrowNotFoundException(id);
        Student updatedStudent = studentMapper.toStudent(student, foundedStudent);
        return studentMapper.toStudentClientResponseBody(studentRepository.save(updatedStudent));
    }

    @Transactional
    public void delete(Long id) {
        studentRepository.delete(findByIdOrThrowNotFoundException(id));
    }

    private Student findByIdOrThrowNotFoundException(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Student not Found"));
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Optional<Student> findByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }

}

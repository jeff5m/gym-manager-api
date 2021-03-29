package com.gymmanager.domain.mapper;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InstructorService.class})
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", source = "instructorId")
    Student toStudent(StudentPostRequestBody studentPostRequestBody, Long instructorId);

    StudentClientResponseBody toStudentClientResponseBody(Student student);
}
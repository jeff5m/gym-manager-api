package com.gymmanager.domain.mapper;

import com.gymmanager.domain.mapper.requests.student.StudentClientResponseBody;
import com.gymmanager.domain.mapper.requests.student.StudentPostRequestBody;
import com.gymmanager.domain.mapper.requests.student.StudentPutRequestBody;
import com.gymmanager.domain.model.Student;
import com.gymmanager.service.InstructorService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {InstructorService.class})
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "instructor", source = "studentPostRequestBody.instructor.id")
    Student toStudent(StudentPostRequestBody studentPostRequestBody);

    @Mapping(target = "avatarUrl", source = "studentPutRequestBody.avatarUrl")
    @Mapping(target = "email", source = "studentPutRequestBody.email")
    Student toStudent(StudentPutRequestBody studentPutRequestBody, Student student);

    @Mapping(target = "instructor.numberOfStudents", source = "instructor.students")
    StudentClientResponseBody toStudentClientResponseBody(Student student);

    List<StudentClientResponseBody> toListOfStudentClientResponseBody(List<Student> students);

    default Long map(List<Student> studentList) {
        return (long) studentList.size();
    }
}

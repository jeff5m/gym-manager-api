package com.gymmanager.api.mapper;

import com.gymmanager.api.mapper.requests.InstructorClientResponseBody;
import com.gymmanager.api.mapper.requests.InstructorPostRequestBody;
import com.gymmanager.api.mapper.requests.InstructorPutRequestBody;
import com.gymmanager.domain.model.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.util.List;

@Mapper(componentModel = "spring")
public interface InstructorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", source = "creationDate")
    Instructor toInstructor(InstructorPostRequestBody instructorPostRequestBody, LocalDate creationDate);

    @Mapping(target = "avatarUrl", source = "instructorPutRequestBody.avatarUrl")
    @Mapping(target = "email", source = "instructorPutRequestBody.email")
    @Mapping(target = "services", source = "instructorPutRequestBody.services")
    Instructor toInstructor(InstructorPutRequestBody instructorPutRequestBody, Instructor instructor);

    InstructorClientResponseBody toInstructorClientResponseBody (Instructor instructor);

    List<InstructorClientResponseBody> toListOfInstructorClientResponseBody (List<Instructor> instructor);
}

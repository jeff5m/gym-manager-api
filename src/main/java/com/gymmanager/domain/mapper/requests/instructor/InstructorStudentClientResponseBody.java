package com.gymmanager.domain.mapper.requests.instructor;

import com.gymmanager.domain.model.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorStudentClientResponseBody {
    private Long id;
    private String name;
    private Integer age;
    private StudentGender gender;
    private String avatarUrl;
}


package com.gymmanager.domain.mapper.requests.student;

import com.gymmanager.domain.mapper.requests.instructor.InstructorClientResponseBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentClientResponseBody {
    private Long id;
    private String name;
    private String avatarUrl;
    private InstructorClientResponseBody instructor;
}

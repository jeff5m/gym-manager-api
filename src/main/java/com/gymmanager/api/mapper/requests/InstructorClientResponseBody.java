package com.gymmanager.api.mapper.requests;

import com.gymmanager.domain.model.InstructorServices;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstructorClientResponseBody {
    private String name;
    private String avatarUrl;
    private InstructorServices services;
    private LocalDate createdAt;
}


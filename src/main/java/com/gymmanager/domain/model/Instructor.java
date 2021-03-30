package com.gymmanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private String avatarUrl;
    @Column(unique = true, nullable = false, length = 60)
    private String email;
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;
    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private InstructorServices services;
    @OneToMany(mappedBy = "instructor")
    private List<Student> students;
    @Column(nullable = false)
    private LocalDate birth;
    @Column(nullable = false)
    private LocalDate createdAt;
}

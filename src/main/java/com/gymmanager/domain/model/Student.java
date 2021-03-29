package com.gymmanager.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Student {
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
    @Digits(integer = 3, fraction = 1)
    @Column(nullable = false)
    private BigDecimal weight;
    @Digits(integer = 3, fraction = 1)
    @Column(nullable = false)
    private BigDecimal height;
    @Column(nullable = false, length = 3)
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private StudentGender gender;
    @ManyToOne(optional = false)
    @JsonBackReference
    private Instructor instructor;
}

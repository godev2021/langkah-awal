package com.langkah_awal.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee_score")
@Getter
@Setter
public class EmployeeScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String name;
    private Double kpiScore;
    private Double reviewScore; // review 360
    private Double kudosScore;
    private Double absenceScore;
    private LocalDateTime timestamp = LocalDateTime.now();
    @Column(columnDefinition = "TEXT")
    private String summarizedReview;
}

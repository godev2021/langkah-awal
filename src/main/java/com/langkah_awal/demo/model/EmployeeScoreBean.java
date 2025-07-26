package com.langkah_awal.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EmployeeScoreBean {
    private Long employeeId;
    private String name;
    private Double kpiScore;
    private Double reviewScore; // review 360
    private Double kudosScore;
    private Double absenceScore;
    private LocalDateTime timestamp = LocalDateTime.now();
}

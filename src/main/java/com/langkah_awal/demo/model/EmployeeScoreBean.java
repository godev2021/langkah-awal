package com.langkah_awal.demo.model;

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
    private Double finalTotalScore;
    private String clustering;
    private LocalDateTime timestamp = LocalDateTime.now();
}

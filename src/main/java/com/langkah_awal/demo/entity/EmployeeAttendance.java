package com.langkah_awal.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee_attendance")
@Getter
@Setter
public class EmployeeAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long employeeId;
    private int totalAbsence;
    private int totalSick;
    private int totalWfh;
    private int totalLateDays;
}

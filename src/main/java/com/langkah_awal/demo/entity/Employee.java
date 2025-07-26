package com.langkah_awal.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String name;
    @Column(unique = true)
    private String nik;
    private Long reportingManager;
    private String jobTitle;
    private String jobLevel;
    private String departmentName;
    private String divisionName;
    private boolean active = true;
    private boolean mvpOfTheYear;
}

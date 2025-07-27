package com.langkah_awal.demo.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAttendanceBean {
    private long id;
    private Long employeeId;
    private int totalAbsence;
    private int totalSick;
    private int totalWfh;
    private int totalLateDays;
}

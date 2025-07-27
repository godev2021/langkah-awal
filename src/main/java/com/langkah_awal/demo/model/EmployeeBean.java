package com.langkah_awal.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmployeeBean {
    private long id;
    private String name;
    private String nik;
    private Long reportingManager;
    private String jobTitle;
    private String jobLevel;
    private String departmentName;
    private String divisionName;
    private boolean active;
    List<ThreeSixtyBean> threeSixtyReviews = new ArrayList<>();
    private String summarizedReview; // review by AI
    private EmployeeScoreBean performance;
    private EmployeeAttendanceBean attendance;
}

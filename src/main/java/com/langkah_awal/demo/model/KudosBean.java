package com.langkah_awal.demo.model;

import jakarta.persistence.Column;
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
public class KudosBean {
    private Long id;
    private Long fromEmployeeId;
    private Long toEmployeeId;
    private String message;
    private String category;
    private String visibility = "PUBLIC";
    private LocalDateTime timestamp = LocalDateTime.now();

    private String fromEmployeeName;
    private String toEmployeeName;
}

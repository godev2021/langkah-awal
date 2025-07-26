package com.langkah_awal.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "kudos")
@Getter
@Setter
public class Kudos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long fromEmployeeId;
    private Long toEmployeeId;
    private String message;
    private String category;
    private String visibility = "PUBLIC";
    private Double score;
    private LocalDateTime timestamp = LocalDateTime.now();
}

package com.langkah_awal.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "kudos", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")
})
@Getter
@Setter
public class Kudos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "from_employee_id", nullable = false)
    private Long fromEmployeeId;

    @Column(name = "to_employee_id", nullable = false)
    private Long toEmployeeId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String visibility = "PUBLIC";

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();
}

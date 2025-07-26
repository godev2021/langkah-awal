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
@Table(name = "three_sixty_review")
@Getter
@Setter
public class ThreeSixtyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Long employeeId;
    private Long employeeReviewId; //employee yang di review
    private Double reviewScore;
    @Column(columnDefinition = "TEXT")
    private String reviewContribution;
    @Column(columnDefinition = "TEXT")
    private String reviewStrength;
    @Column(columnDefinition = "TEXT")
    private String reviewDevelopment;
    //review category
    private String type;
}

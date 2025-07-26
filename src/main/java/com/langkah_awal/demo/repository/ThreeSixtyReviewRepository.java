package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.ThreeSixtyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThreeSixtyReviewRepository extends JpaRepository<ThreeSixtyReview, Long> {
    List<ThreeSixtyReview> findByEmployeeId(Long employeeId);

    List<ThreeSixtyReview> findByEmployeeReviewId(Long employeeReviewId);
}

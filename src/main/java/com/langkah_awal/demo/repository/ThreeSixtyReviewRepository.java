package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.ThreeSixtyReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThreeSixtyReviewRepository extends JpaRepository<ThreeSixtyReview, Long> {
    Optional<ThreeSixtyReview> findByEmployeeId(Long employeeId);
}

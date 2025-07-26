package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.EmployeeScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeScoreRepository extends JpaRepository<EmployeeScore, Long> {
    @Query("SELECT e FROM EmployeeScore e WHERE e.employeeId = :employeeId AND FUNCTION('YEAR', e.timestamp) = FUNCTION('YEAR', CURRENT_DATE)")
    EmployeeScore findThisYearByEmployeeId(@Param("employeeId") Long employeeId);

    Optional<EmployeeScore> findEmployeeScoreByEmployeeId(Long employeeId);
}

package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.EmployeeAttendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeAttendanceRepository extends JpaRepository<EmployeeAttendance, Long> {
    Optional<EmployeeAttendance> findEmployeeAttendanceByEmployeeId(Long employeeId);
}

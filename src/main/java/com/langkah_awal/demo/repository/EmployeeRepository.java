package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findEmployeeById(Long employeeId);

    @Query(value = "SELECT COUNT(*) FROM employee", nativeQuery = true)
    long countTotalEmployees();
}

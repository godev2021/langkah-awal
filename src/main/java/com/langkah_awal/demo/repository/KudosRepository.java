package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.Kudos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KudosRepository extends JpaRepository<Kudos, Long> {
    @Query(
        value = "SELECT * FROM kudos WHERE to_employee_id = :toEmployeeId AND EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE) ORDER BY timestamp DESC",
        nativeQuery = true)
    List<Kudos> findKudosThisYear(@Param("toEmployeeId") long toEmployeeId);

    @Query(
            value = "SELECT * FROM kudos WHERE EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE) ORDER BY timestamp DESC",
            nativeQuery = true)
    List<Kudos> findKudosThisYear();
}

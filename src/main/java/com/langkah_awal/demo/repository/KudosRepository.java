package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.Kudos;
import com.langkah_awal.demo.model.projections.KudosLeaderboardProjections;
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

    List<Kudos> findKudosByToEmployeeId(long employeeId);

    @Query(
            value = "SELECT COUNT(DISTINCT from_employee_id) FROM kudos WHERE to_employee_id = :toEmployeeId AND EXTRACT(YEAR FROM timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)",
            nativeQuery = true)
    int countDistinctKudosGiversThisYear(@Param("toEmployeeId") long toEmployeeId);

    @Query(
            value = "SELECT " +
                    "e.name AS employeeName, " +
                    "COUNT(DISTINCT k.from_employee_id) AS totalKudos, " +
                    "SUM(k.score) AS totalScore " +
                    "FROM kudos k " +
                    "JOIN employee e ON k.to_employee_id = e.id " +
                    "WHERE EXTRACT(YEAR FROM k.timestamp) = EXTRACT(YEAR FROM CURRENT_DATE) " +
                    "GROUP BY k.to_employee_id, e.name " +
                    "ORDER BY totalScore DESC",
            nativeQuery = true)
    List<KudosLeaderboardProjections> findKudosLeaderboardThisYear();
}

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
            value = """
                    SELECT 
                        e.name AS employeeName,
                        k.category AS category,
                        summary.totalKudos,
                        summary.totalScore
                    FROM kudos k
                    JOIN (
                        SELECT 
                            k1.to_employee_id,
                            MAX(k1.timestamp) AS latestTimestamp
                        FROM kudos k1
                        WHERE EXTRACT(YEAR FROM k1.timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)
                        GROUP BY k1.to_employee_id
                    ) latest ON k.to_employee_id = latest.to_employee_id AND k.timestamp = latest.latestTimestamp
                    JOIN (
                        SELECT 
                            k2.to_employee_id,
                            COUNT(DISTINCT k2.from_employee_id) AS totalKudos,
                            SUM(k2.score) AS totalScore
                        FROM kudos k2
                        WHERE EXTRACT(YEAR FROM k2.timestamp) = EXTRACT(YEAR FROM CURRENT_DATE)
                        GROUP BY k2.to_employee_id
                    ) summary ON k.to_employee_id = summary.to_employee_id
                    JOIN employee e ON k.to_employee_id = e.id
                    ORDER BY summary.totalScore DESC
                    """,
            nativeQuery = true)
    List<KudosLeaderboardProjections> findKudosLeaderboardThisYear();
}

package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.EmployeeAttendance;
import com.langkah_awal.demo.entity.EmployeeScore;
import com.langkah_awal.demo.entity.ThreeSixtyReview;
import com.langkah_awal.demo.model.EmployeeScoreBean;
import com.langkah_awal.demo.repository.EmployeeAttendanceRepository;
import com.langkah_awal.demo.repository.EmployeeRepository;
import com.langkah_awal.demo.repository.EmployeeScoreRepository;
import com.langkah_awal.demo.repository.ThreeSixtyReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeScoreService {
    private final EmployeeScoreRepository employeeScoreRepository;
    private final ThreeSixtyReviewRepository threeSixtyReviewRepository;
    private final EmployeeAttendanceRepository employeeAttendanceRepository;
    private final KudosService kudosService;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createOrUpdateEmployeeScore(EmployeeScoreBean employeeScoreBean) {
        employeeScoreRepository.findEmployeeScoreByEmployeeId(employeeScoreBean.getEmployeeId()).ifPresentOrElse(employeeScore -> {
            employeeScore.setEmployeeId(employeeScoreBean.getEmployeeId());
            employeeScore.setName(employeeScoreBean.getName());
            employeeScore.setKpiScore(employeeScoreBean.getKpiScore());
            List<ThreeSixtyReview> threeSixtyReviews = threeSixtyReviewRepository.findByEmployeeReviewId(employeeScoreBean.getEmployeeId());
            double averageThreeSixty = threeSixtyReviews.stream()
                    .mapToDouble(ThreeSixtyReview::getReviewScore)
                    .average()
                    .orElse(0.0);
            employeeScore.setReviewScore(averageThreeSixty);
            double averageKudos = kudosService.calculateKudosScore(employeeScore.getEmployeeId());
            employeeScore.setKudosScore(averageKudos);
            employeeAttendanceRepository.findEmployeeAttendanceByEmployeeId(employeeScoreBean.getEmployeeId()).ifPresent(employeeAttendance -> employeeScore.setAbsenceScore(calculateAbsenceScore(employeeAttendance)));
            employeeScoreRepository.save(employeeScore);
        }, () -> {
            EmployeeScore employeeScore = new EmployeeScore();
            employeeScore.setEmployeeId(employeeScoreBean.getEmployeeId());
            employeeScore.setName(employeeScoreBean.getName());
            employeeScore.setKpiScore(employeeScoreBean.getKpiScore());
            List<ThreeSixtyReview> threeSixtyReviews = threeSixtyReviewRepository.findByEmployeeReviewId(employeeScoreBean.getEmployeeId());
            double averageThreeSixty = threeSixtyReviews.stream()
                    .mapToDouble(ThreeSixtyReview::getReviewScore)
                    .average()
                    .orElse(0.0);
            employeeScore.setReviewScore(averageThreeSixty);
            double averageKudos = kudosService.calculateKudosScore(employeeScore.getEmployeeId());
            employeeScore.setKudosScore(averageKudos);
            employeeAttendanceRepository.findEmployeeAttendanceByEmployeeId(employeeScoreBean.getEmployeeId()).ifPresent(employeeAttendance -> employeeScore.setAbsenceScore(calculateAbsenceScore(employeeAttendance)));
            employeeScoreRepository.save(employeeScore);
        });
    }

    public EmployeeScoreBean getEmployeeScoreById(long employeeId) {
        EmployeeScoreBean employeeScoreBean = new EmployeeScoreBean();
        employeeScoreRepository.findEmployeeScoreByEmployeeId(employeeId).ifPresent(employeeScore -> {
            long totalEmployees = employeeRepository.countTotalEmployees();

            Double kpiScore = to2Decimal(employeeScore.getKpiScore() * 0.7);
            Double reviewScore = to2Decimal((employeeScore.getReviewScore() / 5) * 100 * 0.15);
            Double kudosScore = to2Decimal(employeeScore.getKudosScore() / totalEmployees * 100 * 0.1);
            Double absenceScore = to2Decimal(employeeScore.getAbsenceScore());

            employeeScoreBean.setEmployeeId(employeeScore.getEmployeeId());
            employeeScoreBean.setName(employeeScore.getName());
            employeeScoreBean.setKpiScore(kpiScore);
            employeeScoreBean.setReviewScore(reviewScore);
            employeeScoreBean.setKudosScore(kudosScore);
            employeeScoreBean.setAbsenceScore(absenceScore);
            employeeScoreBean.setFinalTotalScore(kpiScore + reviewScore + kudosScore + absenceScore);
            employeeScoreBean.setClustering(clusteringScore(employeeScoreBean.getFinalTotalScore()));
        });
        return employeeScoreBean;
    }

    public EmployeeScore getEmployeeScoreThisYear(long employeeId) {
        return employeeScoreRepository.findThisYearByEmployeeId(employeeId);
    }

    public static double calculateTotalPenalty(EmployeeAttendance attendance) {
        int totalAbsence = attendance.getTotalAbsence();     // x3 poin
        int totalSick = attendance.getTotalSick();           // jika > 12 → x2 poin
        int totalWfh = attendance.getTotalWfh();             // x1 poin
        int totalLate = attendance.getTotalLateDays();       // jika >12 → 2 + (terlambat - 13)

        int penaltyAbsence = totalAbsence * 3;
        int penaltySick = totalSick > 12 ? 2 : 0;
        int penaltyWfh = totalWfh; // 1 poin per hari
        int penaltyLate = totalLate < 13 ? 0 : 2 + (totalLate - 13);

        return penaltyAbsence + penaltySick + penaltyWfh + penaltyLate;
    }

    public static double calculateAbsenceScore(EmployeeAttendance attendance) {
        double totalPenalty = calculateTotalPenalty(attendance);
        return (100 - totalPenalty) * 0.05;
    }

    public static String clusteringScore(double finalTotalScore) {
        if (finalTotalScore >= 85 && finalTotalScore <= 100) {
            return "Rising Star";
        } else if (finalTotalScore >= 60 && finalTotalScore < 85) {
            return "Perform";
        } else {
            return "Under Perform";
        }
    }

    public static Double to2Decimal(double value) {
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}

package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.Employee;
import com.langkah_awal.demo.entity.EmployeeScore;
import com.langkah_awal.demo.entity.ThreeSixtyReview;
import com.langkah_awal.demo.exception.DuplicateTrackingNumberException;
import com.langkah_awal.demo.model.EmployeeAttendanceBean;
import com.langkah_awal.demo.model.EmployeeBean;
import com.langkah_awal.demo.model.EmployeeScoreBean;
import com.langkah_awal.demo.model.ThreeSixtyBean;
import com.langkah_awal.demo.repository.EmployeeAttendanceRepository;
import com.langkah_awal.demo.repository.EmployeeRepository;
import com.langkah_awal.demo.repository.EmployeeScoreRepository;
import com.langkah_awal.demo.repository.ThreeSixtyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.langkah_awal.demo.service.EmployeeScoreService.clusteringScore;
import static com.langkah_awal.demo.service.EmployeeScoreService.to2Decimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeScoreRepository employeeScoreRepository;
    private final EmployeeRepository employeeRepository;
    private final ThreeSixtyReviewRepository threeSixtyReviewRepository;
    private final EmployeeScoreService employeeScoreService;
    private final PromptService promptService;
    private final EmployeeAttendanceRepository employeeAttendanceRepository;

    @Transactional
    public void createOrUpdateEmployee(@RequestBody EmployeeBean employeeBean) {
        employeeRepository.findById(employeeBean.getId()).ifPresentOrElse(employee -> {
            mapBeanToEntity(employeeBean, employee);
            employeeRepository.save(employee);
        }, () -> {
            Employee newEmployee = new Employee();
            mapBeanToEntity(employeeBean, newEmployee);
            employeeRepository.save(newEmployee);
        });
    }

    public List<EmployeeBean> findAllEmployees() {
        List<EmployeeBean> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employee -> {
            EmployeeBean employeeBean = new EmployeeBean();
            mapEntityToBean(employee, employeeBean);
            employees.add(employeeBean);
        });
        return employees;
    }

    public EmployeeBean findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DuplicateTrackingNumberException("Duplicate employee id: " + id));

        EmployeeBean bean = new EmployeeBean();
        mapEntityToBean(employee, bean);

        List<ThreeSixtyReview> reviews = threeSixtyReviewRepository.findByEmployeeReviewId(employee.getId());
        EmployeeScore employeeScore = employeeScoreService.getEmployeeScoreThisYear(employee.getId());
        List<ThreeSixtyBean> reviewBeans = reviews.stream()
                .map(review -> {
                    ThreeSixtyBean respThreeSixty = new ThreeSixtyBean();
                    respThreeSixty.setEmployeeId(review.getEmployeeId());
                    respThreeSixty.setReviewerId(review.getEmployeeReviewId());
                    respThreeSixty.setReviewScore(review.getReviewScore());
                    respThreeSixty.setReviewContribution(review.getReviewStrength());
                    respThreeSixty.setReviewStrength(review.getReviewStrength());
                    respThreeSixty.setReviewDevelopment(review.getReviewDevelopment());
                    respThreeSixty.setType(review.getType());
                    Employee reviewEmployee = employeeRepository.findEmployeeById(review.getEmployeeId());
                    respThreeSixty.setReviewerName(reviewEmployee.getName());
                    respThreeSixty.setReviewerJobTitle(reviewEmployee.getJobTitle());
                    respThreeSixty.setReviewerDepartmentName(reviewEmployee.getDepartmentName());
                    return respThreeSixty;
                })
                .toList();

        bean.setThreeSixtyReviews(reviewBeans);

        if (null != employeeScore && Strings.isNotEmpty(employeeScore.getSummarizedReview())) {
            bean.setSummarizedReview(employeeScore.getSummarizedReview());
        }

        EmployeeScoreBean employeeScoreBean = new EmployeeScoreBean();
        employeeScoreRepository.findEmployeeScoreByEmployeeId(employee.getId())
                .ifPresent(performance -> {
                    long totalEmployees = employeeRepository.countTotalEmployees();

                    Double kpiScore = to2Decimal(performance.getKpiScore() * 0.7);
                    Double reviewScore = to2Decimal((performance.getReviewScore() / 5) * 100 * 0.15);
                    Double kudosScore = to2Decimal(performance.getKudosScore() / totalEmployees * 100 * 0.1);
                    Double absenceScore = to2Decimal(performance.getAbsenceScore());

                    employeeScoreBean.setEmployeeId(performance.getEmployeeId());
                    employeeScoreBean.setName(performance.getName());
                    employeeScoreBean.setKpiScore(kpiScore);
                    employeeScoreBean.setReviewScore(reviewScore);
                    employeeScoreBean.setKudosScore(kudosScore);
                    employeeScoreBean.setAbsenceScore(absenceScore);
                    employeeScoreBean.setFinalTotalScore(kpiScore + reviewScore + kudosScore + absenceScore);
                    employeeScoreBean.setClustering(clusteringScore(employeeScoreBean.getFinalTotalScore()));
                });

        bean.setPerformance(employeeScoreBean);

        EmployeeAttendanceBean attendanceBean = new EmployeeAttendanceBean();
        employeeAttendanceRepository.findEmployeeAttendanceByEmployeeId(employeeScoreBean.getEmployeeId())
                .ifPresent(employeeAttendance -> {
                    attendanceBean.setTotalAbsence(employeeAttendance.getTotalAbsence());
                    attendanceBean.setTotalWfh(employeeAttendance.getTotalWfh());
                    attendanceBean.setTotalSick(employeeAttendance.getTotalSick());
                    attendanceBean.setTotalLateDays(employeeAttendance.getTotalLateDays());
                });
        bean.setAttendance(attendanceBean);
        return bean;
    }

    @Async
    public void summarizeEmployee(long employeeId) {
        String employeeName = employeeRepository.findEmployeeById(employeeId).getName();
        List<ThreeSixtyReview> threeSixtyReviews = threeSixtyReviewRepository.findByEmployeeId(employeeId);
        List<String> feedbacks = new ArrayList<>();

        threeSixtyReviews.forEach(threeSixtyReview -> {
            feedbacks.add(threeSixtyReview.getReviewStrength());
            feedbacks.add(threeSixtyReview.getReviewContribution());
            feedbacks.add(threeSixtyReview.getReviewDevelopment());
        });

        EmployeeScore employeeScore = employeeScoreService.getEmployeeScoreThisYear(employeeId);

        if (null != employeeScore) {
            if (Strings.isEmpty(employeeScore.getSummarizedReview())) {
                String summarizedReview = promptService.summarizeFeedbackPrompt(employeeName, feedbacks);
                employeeScore.setSummarizedReview(summarizedReview);
                employeeScoreRepository.save(employeeScore);
            }
            return;
        }

        String summarizedReview = promptService.summarizeFeedbackPrompt(employeeName, feedbacks);
        EmployeeScore newEmployeeScore = new EmployeeScore();
        newEmployeeScore.setEmployeeId(employeeId);
        newEmployeeScore.setName(employeeName);
        newEmployeeScore.setSummarizedReview(summarizedReview);
        employeeScoreRepository.save(newEmployeeScore);
    }

    private void mapBeanToEntity(EmployeeBean bean, Employee entity) {
        entity.setNik(bean.getNik());
        entity.setName(bean.getName());
        entity.setReportingManager(bean.getReportingManager());
        entity.setJobTitle(bean.getJobTitle());
        entity.setJobLevel(bean.getJobLevel());
        entity.setDepartmentName(bean.getDepartmentName());
        entity.setDivisionName(bean.getDivisionName());
        entity.setActive(bean.isActive());
    }

    private void mapEntityToBean(Employee entity, EmployeeBean bean) {
        bean.setId(entity.getId());
        bean.setNik(entity.getNik());
        bean.setName(entity.getName());
        bean.setReportingManager(entity.getReportingManager());
        bean.setJobTitle(entity.getJobTitle());
        bean.setJobLevel(entity.getJobLevel());
        bean.setDepartmentName(entity.getDepartmentName());
        bean.setDivisionName(entity.getDivisionName());
        bean.setActive(entity.isActive());
    }
}

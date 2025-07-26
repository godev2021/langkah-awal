package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.Employee;
import com.langkah_awal.demo.entity.EmployeeScore;
import com.langkah_awal.demo.entity.ThreeSixtyReview;
import com.langkah_awal.demo.exception.DuplicateTrackingNumberException;
import com.langkah_awal.demo.model.EmployeeBean;
import com.langkah_awal.demo.model.ThreeSixtyBean;
import com.langkah_awal.demo.repository.EmployeeRepository;
import com.langkah_awal.demo.repository.EmployeeScoreRepository;
import com.langkah_awal.demo.repository.ThreeSixtyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeScoreRepository employeeScoreRepository;
    private final EmployeeRepository employeeRepository;
    private final ThreeSixtyReviewRepository threeSixtyReviewRepository;
    private final EmployeeScoreService employeeScoreService;
    private final PromptService promptService;

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
                    respThreeSixty.setEmployeeId(employee.getId());
                    respThreeSixty.setReviewerId(review.getEmployeeReviewId());
                    respThreeSixty.setReviewScore(review.getReviewScore());
                    respThreeSixty.setReviewContribution(review.getReviewStrength());
                    respThreeSixty.setReviewStrength(review.getReviewStrength());
                    respThreeSixty.setReviewDevelopment(review.getReviewDevelopment());
                    respThreeSixty.setType(review.getType());
                    return respThreeSixty;
                })
                .toList();

        bean.setThreeSixtyReviews(reviewBeans);

        if (null != employeeScore && Strings.isNotEmpty(employeeScore.getSummarizedReview())) {
            bean.setSummarizedReview(employeeScore.getSummarizedReview());
        }

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

        String summarizedReview = promptService.summarizeFeedbackPrompt(employeeName, feedbacks);
        EmployeeScore employeeScore = employeeScoreService.getEmployeeScoreThisYear(employeeId);
        if (null != employeeScore) {
            employeeScore.setSummarizedReview(summarizedReview);
            employeeScoreRepository.save(employeeScore);
        } else {
            EmployeeScore newEmployeeScore = new EmployeeScore();
            newEmployeeScore.setEmployeeId(employeeId);
            newEmployeeScore.setName(employeeName);
            newEmployeeScore.setSummarizedReview(summarizedReview);
            employeeScoreRepository.save(newEmployeeScore);
        }
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

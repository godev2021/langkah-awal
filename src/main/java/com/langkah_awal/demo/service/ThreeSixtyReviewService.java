package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.ThreeSixtyReview;
import com.langkah_awal.demo.model.ThreeSixtyBean;
import com.langkah_awal.demo.repository.ThreeSixtyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThreeSixtyReviewService {
    private final ThreeSixtyReviewRepository threeSixtyReviewRepository;

    @Transactional
    public void create(ThreeSixtyBean threeSixtyBean) {
        ThreeSixtyReview threeSixtyReview = new ThreeSixtyReview();
        threeSixtyReview.setEmployeeId(null != threeSixtyBean.getEmployeeId() ? threeSixtyBean.getEmployeeId() : 1);
        threeSixtyReview.setEmployeeReviewId(null != threeSixtyBean.getReviewerId() ? threeSixtyBean.getReviewerId() : 3);
        threeSixtyReview.setReviewScore(threeSixtyBean.getReviewScore());
        threeSixtyReview.setReviewContribution(threeSixtyBean.getReviewContribution());
        threeSixtyReview.setReviewStrength(threeSixtyBean.getReviewStrength());
        threeSixtyReview.setReviewDevelopment(threeSixtyBean.getReviewDevelopment());
        threeSixtyReview.setType(threeSixtyBean.getType());
        threeSixtyReviewRepository.save(threeSixtyReview);
    }

    @Transactional
    public void createBulk(List<ThreeSixtyBean> threeSixtyBeans) {
        long employeeId = 14L;

        for (ThreeSixtyBean threeSixtyBean : threeSixtyBeans) {
            ThreeSixtyReview threeSixtyReview = new ThreeSixtyReview();
            threeSixtyReview.setEmployeeId(employeeId);
            threeSixtyReview.setEmployeeReviewId(3L);
            threeSixtyReview.setReviewScore(threeSixtyBean.getReviewScore());
            threeSixtyReview.setReviewContribution(threeSixtyBean.getReviewContribution());
            threeSixtyReview.setReviewStrength(threeSixtyBean.getReviewStrength());
            threeSixtyReview.setReviewDevelopment(threeSixtyBean.getReviewDevelopment());
            threeSixtyReview.setType(threeSixtyBean.getType());
            threeSixtyReviewRepository.save(threeSixtyReview);
            employeeId++;
        }
    }
}

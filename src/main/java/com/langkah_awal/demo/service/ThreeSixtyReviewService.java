package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.ThreeSixtyReview;
import com.langkah_awal.demo.model.ThreeSixtyBean;
import com.langkah_awal.demo.repository.ThreeSixtyReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThreeSixtyReviewService {
    private final ThreeSixtyReviewRepository threeSixtyReviewRepository;

    @Transactional
    public void create(ThreeSixtyBean threeSixtyBean) {
        ThreeSixtyReview threeSixtyReview = new ThreeSixtyReview();
        threeSixtyReview.setEmployeeId(threeSixtyBean.getEmployeeId());
        threeSixtyReview.setReviewScore(threeSixtyBean.getReviewScore());
        threeSixtyReview.setReviewContribution(threeSixtyBean.getReviewContribution());
        threeSixtyReview.setReviewStrength(threeSixtyBean.getReviewStrength());
        threeSixtyReview.setReviewDevelopment(threeSixtyBean.getReviewDevelopment());
        threeSixtyReviewRepository.save(threeSixtyReview);
    }
}

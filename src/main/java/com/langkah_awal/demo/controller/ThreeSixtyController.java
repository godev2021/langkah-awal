package com.langkah_awal.demo.controller;

import com.langkah_awal.demo.model.ThreeSixtyBean;
import com.langkah_awal.demo.service.ThreeSixtyReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/three-sixty")
public class ThreeSixtyController {
    private final ThreeSixtyReviewService threeSixtyReviewService;

    @PostMapping("")
    public ResponseEntity<?> createThreeSixty(@RequestBody ThreeSixtyBean threeSixtyBean) {
        threeSixtyReviewService.create(threeSixtyBean);
        return ResponseEntity.ok().build();
    }
}

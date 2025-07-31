package com.langkah_awal.demo.controller;

import com.langkah_awal.demo.model.KudosBean;
import com.langkah_awal.demo.model.general.ApiResponse;
import com.langkah_awal.demo.model.projections.KudosLeaderboardProjections;
import com.langkah_awal.demo.service.KudosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api/kudos"})
public class KudosController {
    private final KudosService kudosService;

    @PostMapping("")
    public ResponseEntity<?> createKudos(@RequestBody KudosBean kudosBean) {
        kudosService.create(kudosBean);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<List<KudosBean>>> listKudos(@PathVariable long employeeId) {
        List<KudosBean> kudosList = kudosService.findKudosThisYear(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(true, kudosList, null));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<KudosBean>>> listKudos() {
        List<KudosBean> kudosList = kudosService.findKudosThisYear();
        return ResponseEntity.ok(new ApiResponse<>(true, kudosList, null));
    }

    @GetMapping("/count/{employeeId}")
    public ResponseEntity<ApiResponse<Double>> countKudos(@PathVariable long employeeId) {
        double score = kudosService.calculateKudosScore(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(true, score, null));
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<ApiResponse<List<KudosLeaderboardProjections>>> getLeaderboard() {
        List<KudosLeaderboardProjections> kudosLeaderboardThisYear = kudosService.getKudosLeaderboardThisYear();
        return ResponseEntity.ok(new ApiResponse<>(true, kudosLeaderboardThisYear, null));

    }
}

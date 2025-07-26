package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.EmployeeScore;
import com.langkah_awal.demo.repository.EmployeeScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeScoreService {
    private final EmployeeScoreRepository employeeScoreRepository;

    public List<EmployeeScore> getEmployeeScoreThisYear(long employeeId) {
        return employeeScoreRepository.findThisYearByEmployeeId(employeeId);
    }
}

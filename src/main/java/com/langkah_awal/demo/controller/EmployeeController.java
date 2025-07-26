package com.langkah_awal.demo.controller;

import com.langkah_awal.demo.model.EmployeeBean;
import com.langkah_awal.demo.model.general.ApiResponse;
import com.langkah_awal.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<?> createEmployee(@RequestBody EmployeeBean employeeBean) {
        employeeService.createOrUpdateEmployee(employeeBean);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<EmployeeBean>>> listEmployees() {
        List<EmployeeBean> employees = employeeService.findAllEmployees();
        return ResponseEntity.ok(new ApiResponse<>(true, employees, null));
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<ApiResponse<EmployeeBean>> getEmployee(@PathVariable long employeeId) {
        EmployeeBean employeeBean = employeeService.findEmployeeById(employeeId);
        return ResponseEntity.ok(new ApiResponse<>(true, employeeBean, null));
    }
}

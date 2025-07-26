package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.Employee;
import com.langkah_awal.demo.exception.DuplicateTrackingNumberException;
import com.langkah_awal.demo.model.EmployeeBean;
import com.langkah_awal.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createOrUpdateEmployee(@RequestBody EmployeeBean employeeBean) {
        employeeRepository.findByNik(employeeBean.getNik()).ifPresentOrElse(employee -> {
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

    public EmployeeBean findEmployeeByNik(String nik) {
        return employeeRepository.findByNik(nik)
                .map(employee -> {
                    EmployeeBean bean = new EmployeeBean();
                    mapEntityToBean(employee, bean);
                    return bean;
                })
                .orElseThrow(() -> new DuplicateTrackingNumberException("Duplicate employee nik: " + nik));
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
        bean.setEmployeeId(entity.getEmployeeId());
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

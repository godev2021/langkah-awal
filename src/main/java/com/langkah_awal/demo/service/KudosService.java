package com.langkah_awal.demo.service;

import com.langkah_awal.demo.entity.Kudos;
import com.langkah_awal.demo.model.KudosBean;
import com.langkah_awal.demo.repository.EmployeeRepository;
import com.langkah_awal.demo.repository.KudosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KudosService {
    private final KudosRepository kudosRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Kudos create(KudosBean kudosBean) {
        Kudos kudos = new Kudos();
        kudos.setToEmployeeId(kudosBean.getToEmployeeId());
        kudos.setFromEmployeeId(kudosBean.getFromEmployeeId());
        kudos.setMessage(kudosBean.getMessage());
        kudos.setCategory(kudosBean.getCategory());
        kudos.setVisibility(kudosBean.getVisibility());
        kudos.setScore(kudosScore(kudos.getFromEmployeeId()));
        return kudosRepository.save(kudos);
    }

    public List<KudosBean> findKudosThisYear() {
        List<Kudos> kudosList = kudosRepository.findKudosThisYear();
        return this.getKudosBeans(kudosList);
    }

    public List<KudosBean> findKudosThisYear(long toEmployeeId) {
        List<Kudos> kudosList = kudosRepository.findKudosThisYear(toEmployeeId);
        return this.getKudosBeans(kudosList);
    }

    private List<KudosBean> getKudosBeans(List<Kudos> kudosList) {
        List<KudosBean> kudosBeanList = new ArrayList<>(kudosList.size());

        kudosList.forEach(kudos -> {
            String fromEmployeeName = employeeRepository.findEmployeeByEmployeeId(kudos.getFromEmployeeId()).getName();
            String toEmployeeName = employeeRepository.findEmployeeByEmployeeId(kudos.getToEmployeeId()).getName();
            kudosBeanList.add( mapEntityToBean(kudos, fromEmployeeName, toEmployeeName));
        });

        return kudosBeanList;
    }

    public double calculateKudosScore(long employeeId) {
        return kudosRepository.findKudosThisYear(employeeId)
                .stream()
                .mapToDouble(Kudos::getScore)
                .sum();
    }

    private double kudosScore(long fromEmployeeId) {
        String jobLevel = employeeRepository.findEmployeeByEmployeeId(fromEmployeeId).getJobLevel();

        return switch (jobLevel) {
            case "Manager" -> 3;
            case "C Level" -> 5;
            default -> 1;
        };
    }

    private KudosBean mapEntityToBean(Kudos kudos, String fromEmployeeName, String toEmployeeName) {
        KudosBean kudosBean = new KudosBean();
        kudosBean.setId(kudos.getId());
        kudosBean.setFromEmployeeId(kudos.getFromEmployeeId());
        kudosBean.setToEmployeeId(kudos.getToEmployeeId());
        kudosBean.setMessage(kudos.getMessage());
        kudosBean.setCategory(kudos.getCategory());
        kudosBean.setVisibility(kudos.getVisibility());
        kudosBean.setTimestamp(kudos.getTimestamp());
        kudosBean.setFromEmployeeName(fromEmployeeName);
        kudosBean.setToEmployeeName(toEmployeeName);
        return kudosBean;
    }

    private Kudos mapBeanToEntity(KudosBean kudosBean) {
        Kudos kudos = new Kudos();
        kudos.setToEmployeeId(kudosBean.getToEmployeeId());
        kudos.setFromEmployeeId(kudosBean.getFromEmployeeId());
        kudos.setMessage(kudosBean.getMessage());
        kudos.setCategory(kudosBean.getCategory());
        kudos.setVisibility(kudosBean.getVisibility());
        return kudos;
    }
}

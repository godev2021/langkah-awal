package com.langkah_awal.demo.model.request;

import lombok.Data;

import java.util.List;

@Data
public class PromptRequest {
    String prompt;

    @Data
    public static class Employee {
        String employeeName;
        List<String> feedbacks;
    }
}

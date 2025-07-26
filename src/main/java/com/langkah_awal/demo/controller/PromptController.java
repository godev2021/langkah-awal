package com.langkah_awal.demo.controller;

import com.langkah_awal.demo.model.request.PromptRequest;
import com.langkah_awal.demo.service.PromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/api"})
public class PromptController {
    private final PromptService promptService;

    @PostMapping({"/generate"})
    public Mono<String> generate(@RequestBody PromptRequest.Employee request) {
        return this.promptService.summarizeFeedbackPrompt(request.getEmployeeName(), request.getFeedbacks());
    }
}

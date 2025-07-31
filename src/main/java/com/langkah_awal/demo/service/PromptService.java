package com.langkah_awal.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PromptService {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(PromptService.class);
    private final WebClient webClient;

    @Value("${gemini.api-key}")
    private String geminiApiKey;
    @Value("${gemini.endpoint}")
    private String geminiEndpoint;

    public PromptService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(this.geminiEndpoint).build();
    }

    public String askGemini(String prompt) {
        try {
            Thread.sleep(1000);
            Map<String, Object> requestBody = Map.of("contents", List.of(Map.of("parts", List.of(Map.of("text", prompt)))));
            return this.webClient
                    .post()
                    .uri(this.geminiEndpoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-goog-api-key", this.geminiApiKey)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map((response) -> {
                        try {
                            ObjectMapper objectMapper = new ObjectMapper();
                            JsonNode root = objectMapper.readTree(response);
                            return root.path("candidates").path(0).path("content").path("parts").path(0).path("text").asText();
                        } catch (Exception e) {
                            log.error(e.getMessage());
                            return e.getMessage();
                        }
                    }).block();
        } catch (Exception e) {
            log.error(e.getMessage());
            return e.getMessage();
        }
    }

    public String summarizeFeedbackPrompt(String employeeName, List<String> feedbacks) {
        String feedbackList = feedbacks.stream()
                .map(f -> "- \"" + f + "\"")
                .collect(Collectors.joining("\n"));

        String prompt =
        """
            Saya akan memberikan hasil penilaian 360 derajat dari beberapa rekan kerja terhadap seorang karyawan bernama %s.
            Tolong lakukan analisa berdasarkan pendekatan SWOT (Strength, Weakness, Opportunity, Threat), Emotion & Sentiment Analysis terhadap feedback yang ada, Insight untuk perkembangan karir dan rekomendasikan sertifikasi atau pelatihan yang cocok jika perlu, dan hasilkan ringkasan dengan format yang SAMA dan TERSTRUKTUR seperti di bawah ini.
            Tampilkan hasilnya dalam bentuk teks biasa, tanpa ada enter, tanpa simbol atau karakter tambahan seperti bintang (*), backtick (`), atau markdown lainnya.
        
            Format yang harus diikuti:
            1. Strength:
            2. Weakness:
            3. Opportunity:
            4. Threat:
            5. Emotion & Sentiment Analysis:
            6. Insight:
            7. Summarize:
                
            Berikut adalah feedback dari beberapa rekan kerja:
            %s
        
            Tampilkan hasil akhir dengan urutan dan label poin 1 sampai 7 seperti di atas, dalam format teks bersih dan rapi.
        """.formatted(employeeName, feedbackList);

        return this.askGemini(prompt);
    }
}


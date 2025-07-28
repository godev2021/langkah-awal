package com.langkah_awal.demo.controller;

import com.langkah_awal.demo.service.DatabaseHelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debug")
public class DebugController {
    private final DatabaseHelperService databaseHelperService;

    @GetMapping("/reset-db")
    public ResponseEntity<String> resetDatabase() {
        try {
            databaseHelperService.resetDatabase();
            return ResponseEntity.ok("Database reset successful.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}

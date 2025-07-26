package com.langkah_awal.demo.model.general;

public record ApiResponse<T>(
        boolean success,
        T data,
        ApiErrorResponse error
) {}


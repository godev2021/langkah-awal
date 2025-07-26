package com.langkah_awal.demo.exception;

public class DuplicateTrackingNumberException extends RuntimeException {
    public DuplicateTrackingNumberException(String message) {
        super(message);
    }
}

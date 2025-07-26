package com.langkah_awal.demo.repository;

import com.langkah_awal.demo.entity.TrackingNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumberEntity, Long> {
    Optional<TrackingNumberEntity> findByTrackingNumber(String trackingNumber);
}

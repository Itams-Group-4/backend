package com.itam.itam.repository.inventory;

import com.itam.itam.entity.inventory.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LicenseRepository extends JpaRepository<License, Long> {
    List<License> findByAssetId(Long assetId);
    List<License> findByStatus(String status);
    List<License> findByExpirationDateBetween(LocalDate startDate, LocalDate endDate);
}
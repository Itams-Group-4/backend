package com.itam.itam.service;

import com.itam.itam.dto.dashboard.DashboardSummaryResponse;
import com.itam.itam.repository.inventory.AssetRepository;
import com.itam.itam.repository.inventory.LicenseRepository;
import com.itam.itam.repository.inventory.MaintenanceRepository;
import com.itam.itam.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final AssetRepository assetRepository;
    private final UserRepository userRepository;
    private final LicenseRepository licenseRepository;
    private final MaintenanceRepository maintenanceRepository;

    public DashboardService(
            AssetRepository assetRepository,
            UserRepository userRepository,
            LicenseRepository licenseRepository,
            MaintenanceRepository maintenanceRepository
    ) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
        this.licenseRepository = licenseRepository;
        this.maintenanceRepository = maintenanceRepository;
    }

    public DashboardSummaryResponse getSummary() {
        return DashboardSummaryResponse.builder()
                .totalAssets(assetRepository.count())
                .activeAssets(assetRepository.countByStatus("active"))
                .inactiveAssets(assetRepository.countByStatus("inactive"))
                .retiredAssets(assetRepository.countByStatus("retired"))
                .assetsInMaintenance(assetRepository.countByStatus("in_maintenance"))
                .totalUsers(userRepository.count())
                .totalLicenses(licenseRepository.count())
                .totalMaintenances(maintenanceRepository.count())
                .build();
    }
}
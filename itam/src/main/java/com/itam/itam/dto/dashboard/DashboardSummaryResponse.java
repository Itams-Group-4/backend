package com.itam.itam.dto.dashboard;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashboardSummaryResponse {
    private long totalAssets;
    private long activeAssets;
    private long inactiveAssets;
    private long retiredAssets;
    private long assetsInMaintenance;

    private long totalUsers;
    private long totalLicenses;
    private long totalMaintenances;
}
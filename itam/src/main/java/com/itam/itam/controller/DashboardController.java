package com.itam.itam.controller;

import com.itam.itam.dto.dashboard.DashboardSummaryResponse;
import com.itam.itam.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumen")
    public DashboardSummaryResponse getSummary() {
        return dashboardService.getSummary();
    }
}
package com.controller;

import com.resourse.ActivityDTO;
import com.resourse.LowStockAlertDTO;
import com.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashBordController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/total-rice-stock")
    public ResponseEntity<Map<String, Object>> getTotalRiceStock() {
        return ResponseEntity.ok(dashboardService.getTotalRiceStock());
    }

    @GetMapping("/total-paddy-stock")
    public ResponseEntity<Map<String, Object>> getTotalPaddyStock() {
        return ResponseEntity.ok(dashboardService.getTotalPaddyStock());
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<Map<String, Object>> getTotalRevenue() {
        return ResponseEntity.ok(dashboardService.getTotalRevenue());
    }

    @GetMapping("/total-warehouses")
    public ResponseEntity<Map<String, Object>> getTotalWarehouses() {
        return ResponseEntity.ok(dashboardService.getTotalWarehouses());
    }

    @GetMapping("/recent-activities")
    public ResponseEntity<List<ActivityDTO>> getRecentActivities() {
        return ResponseEntity.ok(dashboardService.getRecentActivities());
    }

    @GetMapping("/low-stock-alerts")
    public ResponseEntity<List<LowStockAlertDTO>> getLowStockAlerts() {
        return ResponseEntity.ok(dashboardService.getLowStockAlerts());
    }

    
}

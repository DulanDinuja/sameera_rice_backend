package com.service;

import com.resourse.ActivityDTO;
import com.resourse.LowStockAlertDTO;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    Map<String, Object> getTotalRiceStock();
    Map<String, Object> getTotalPaddyStock();
    Map<String, Object> getTotalRevenue();
    Map<String, Object> getTotalWarehouses();
    List<ActivityDTO> getRecentActivities();
    List<LowStockAlertDTO> getLowStockAlerts();
}

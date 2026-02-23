package com.service;

import com.resourse.ReportRequest;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String, Object>> generateReport(ReportRequest request);
    List<String> getWarehouses();
    List<String> getPaddyTypes();
    List<String> getRiceTypes();
    List<String> getCustomers();
    List<String> getSuppliers();
}

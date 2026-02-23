package com.controller;

import com.resourse.ReportRequest;
import com.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/generate")
    public ResponseEntity<List<Map<String, Object>>> generateReport(@RequestBody ReportRequest request) {
        return ResponseEntity.ok(reportService.generateReport(request));
    }

    @GetMapping("/warehouses")
    public ResponseEntity<List<String>> getWarehouses() {
        return ResponseEntity.ok(reportService.getWarehouses());
    }

    @GetMapping("/paddy-types")
    public ResponseEntity<List<String>> getPaddyTypes() {
        return ResponseEntity.ok(reportService.getPaddyTypes());
    }

    @GetMapping("/rice-types")
    public ResponseEntity<List<String>> getRiceTypes() {
        return ResponseEntity.ok(reportService.getRiceTypes());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<String>> getCustomers() {
        return ResponseEntity.ok(reportService.getCustomers());
    }

    @GetMapping("/suppliers")
    public ResponseEntity<List<String>> getSuppliers() {
        return ResponseEntity.ok(reportService.getSuppliers());
    }
}

package com.controller;

import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/warehouse")
@CrossOrigin(origins = "*")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/stats")
    public ResponseEntity<List<Map<String, Object>>> getWarehouseStats() {
        return ResponseEntity.ok(warehouseService.getWarehouseStats());
    }
}

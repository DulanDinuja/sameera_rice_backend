package com.service.impl;

import com.repository.PaddyRepository;
import com.repository.PaddySaleRepository;
import com.repository.PaddyThreshingRepository;
import com.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private PaddyRepository paddyRepository;

    @Autowired
    private PaddySaleRepository paddySaleRepository;

    @Autowired
    private PaddyThreshingRepository paddyThreshingRepository;

    @Override
    public List<Map<String, Object>> getWarehouseStats() {
        List<String> warehouses = paddyRepository.findDistinctWarehouses();
        
        return warehouses.stream().map(warehouse -> {
            // Calculate stock: additions - (sales + threshing)
            int totalStock = paddyRepository.findAll().stream()
                .filter(s -> warehouse.equals(s.getWarehouse()))
                .mapToInt(s -> s.getQuantity())
                .sum();
            
            int totalSales = paddySaleRepository.findAll().stream()
                .filter(s -> warehouse.equals(s.getWarehouse()))
                .mapToInt(s -> s.getQuantity())
                .sum();
            
            int totalThreshing = paddyThreshingRepository.findAll().stream()
                .filter(t -> warehouse.equals(t.getWarehouse()))
                .mapToInt(t -> t.getPaddyQuantity())
                .sum();
            
            int currentStock = totalStock - totalSales - totalThreshing;
            int totalCapacity = 1000000;
            double capacityPercentage = Math.round((currentStock * 100.0) / totalCapacity * 10.0) / 10.0;
            
            Map<String, Object> stats = new HashMap<>();
            stats.put("warehouse", warehouse);
            stats.put("currentStock", currentStock);
            stats.put("totalCapacity", totalCapacity);
            stats.put("capacityPercentage", capacityPercentage);
            stats.put("temperature", 23);
            stats.put("humidity", 55);
            
            return stats;
        }).collect(Collectors.toList());
    }
}

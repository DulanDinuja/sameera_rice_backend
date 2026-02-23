package com.service.impl;

import com.domain.*;
import com.repository.*;
import com.resourse.ActivityDTO;
import com.resourse.LowStockAlertDTO;
import com.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private RiceRepository riceRepository;

    @Autowired
    private PaddyRepository paddyRepository;

    @Autowired
    private RiceSaleRepository riceSaleRepository;

    @Autowired
    private PaddySaleRepository paddySaleRepository;

    @Autowired
    private PaddyThreshingRepository paddyThreshingRepository;

    @Override
    public Map<String, Object> getTotalRiceStock() {
        Integer totalStock = riceRepository.getTotalRiceStock();
        if (totalStock == null) totalStock = 0;

        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        
        Integer currentMonthStock = riceRepository.getRiceStockByMonth(now.getMonthValue(), now.getYear());
        Integer lastMonthStock = riceRepository.getRiceStockByMonth(lastMonth.getMonthValue(), lastMonth.getYear());
        
        if (currentMonthStock == null) currentMonthStock = 0;
        if (lastMonthStock == null) lastMonthStock = 0;

        double percentageChange = 0.0;
        if (lastMonthStock > 0) {
            percentageChange = ((double) (currentMonthStock - lastMonthStock) / lastMonthStock) * 100;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalStock", totalStock);
        response.put("percentageChange", Math.round(percentageChange * 100.0) / 100.0);
        response.put("isIncrease", percentageChange >= 0);
        
        return response;
    }

    @Override
    public Map<String, Object> getTotalPaddyStock() {
        Integer totalStock = paddyRepository.getTotalPaddyStock();
        if (totalStock == null) totalStock = 0;

        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        
        Integer currentMonthStock = paddyRepository.getPaddyStockByMonth(now.getMonthValue(), now.getYear());
        Integer lastMonthStock = paddyRepository.getPaddyStockByMonth(lastMonth.getMonthValue(), lastMonth.getYear());
        
        if (currentMonthStock == null) currentMonthStock = 0;
        if (lastMonthStock == null) lastMonthStock = 0;

        double percentageChange = 0.0;
        if (lastMonthStock > 0) {
            percentageChange = ((double) (currentMonthStock - lastMonthStock) / lastMonthStock) * 100;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalStock", totalStock);
        response.put("percentageChange", Math.round(percentageChange * 100.0) / 100.0);
        response.put("isIncrease", percentageChange >= 0);
        
        return response;
    }

    @Override
    public Map<String, Object> getTotalRevenue() {
        Double riceRevenue = riceSaleRepository.getTotalRevenue();
        Double paddyRevenue = paddySaleRepository.getTotalRevenue();
        
        if (riceRevenue == null) riceRevenue = 0.0;
        if (paddyRevenue == null) paddyRevenue = 0.0;
        
        double totalRevenue = riceRevenue + paddyRevenue;

        LocalDate now = LocalDate.now();
        LocalDate lastMonth = now.minusMonths(1);
        
        Double currentRiceRevenue = riceSaleRepository.getRevenueByMonth(now.getMonthValue(), now.getYear());
        Double currentPaddyRevenue = paddySaleRepository.getRevenueByMonth(now.getMonthValue(), now.getYear());
        Double lastRiceRevenue = riceSaleRepository.getRevenueByMonth(lastMonth.getMonthValue(), lastMonth.getYear());
        Double lastPaddyRevenue = paddySaleRepository.getRevenueByMonth(lastMonth.getMonthValue(), lastMonth.getYear());
        
        if (currentRiceRevenue == null) currentRiceRevenue = 0.0;
        if (currentPaddyRevenue == null) currentPaddyRevenue = 0.0;
        if (lastRiceRevenue == null) lastRiceRevenue = 0.0;
        if (lastPaddyRevenue == null) lastPaddyRevenue = 0.0;
        
        double currentMonthRevenue = currentRiceRevenue + currentPaddyRevenue;
        double lastMonthRevenue = lastRiceRevenue + lastPaddyRevenue;

        double percentageChange = 0.0;
        if (lastMonthRevenue > 0) {
            percentageChange = ((currentMonthRevenue - lastMonthRevenue) / lastMonthRevenue) * 100;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("totalRevenue", Math.round(totalRevenue * 100.0) / 100.0);
        response.put("percentageChange", Math.round(percentageChange * 100.0) / 100.0);
        response.put("isIncrease", percentageChange >= 0);
        
        return response;
    }

    @Override
    public Map<String, Object> getTotalWarehouses() {
        Long warehouseCount = paddyRepository.countDistinctWarehouses();
        if (warehouseCount == null) warehouseCount = 0L;

        Map<String, Object> response = new HashMap<>();
        response.put("totalWarehouses", warehouseCount);
        
        return response;
    }

    @Override
    public List<ActivityDTO> getRecentActivities() {
        List<ActivityDTO> activities = new ArrayList<>();
        
        List<RiceStock> riceStocks = riceRepository.findTop3ByOrderByIdDesc();
        for (RiceStock stock : riceStocks) {
            activities.add(new ActivityDTO("Rice Stock", "Stock added: " + stock.getRiceType(), stock.getDate()));
        }
        
        List<PaddyStock> paddyStocks = paddyRepository.findTop3ByOrderByIdDesc();
        for (PaddyStock stock : paddyStocks) {
            activities.add(new ActivityDTO("Paddy Stock", "Stock added: " + stock.getPaddyType(), stock.getDate()));
        }
        
        List<RiceSale> riceSales = riceSaleRepository.findTop3ByOrderByIdDesc();
        for (RiceSale sale : riceSales) {
            activities.add(new ActivityDTO("Rice Sale", "Sale: " + sale.getRiceType(), sale.getDate()));
        }
        
        List<PaddySale> paddySales = paddySaleRepository.findTop3ByOrderByIdDesc();
        for (PaddySale sale : paddySales) {
            activities.add(new ActivityDTO("Paddy Sale", "Sale: " + sale.getPaddyType(), sale.getDate()));
        }
        
        List<PaddyThreshing> threshings = paddyThreshingRepository.findTop3ByOrderByIdDesc();
        for (PaddyThreshing threshing : threshings) {
            activities.add(new ActivityDTO("Threshing", "Threshing: " + threshing.getPaddyType(), threshing.getDate()));
        }
        
        activities.sort((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()));
        
        return activities.size() > 3 ? activities.subList(0, 3) : activities;
    }

    @Override
    public List<LowStockAlertDTO> getLowStockAlerts() {
        List<LowStockAlertDTO> alerts = new ArrayList<>();
        int threshold = 500;
        
        List<RiceStock> lowRiceStocks = riceRepository.findLowStockItems(threshold);
        for (RiceStock stock : lowRiceStocks) {
            String alertLevel = stock.getQuantity() < 500 ? "Critical" : stock.getQuantity() < 1000 ? "Low" : "Medium";
            alerts.add(new LowStockAlertDTO(stock.getRiceType(), stock.getQuantity(), alertLevel));
        }
        
        List<PaddyStock> lowPaddyStocks = paddyRepository.findLowStockItems(threshold);
        for (PaddyStock stock : lowPaddyStocks) {
            String alertLevel = stock.getQuantity() < 500 ? "Critical" : stock.getQuantity() < 1000 ? "Low" : "Medium";
            alerts.add(new LowStockAlertDTO(stock.getPaddyType(), stock.getQuantity(), alertLevel));
        }
        
        return alerts;
    }
}

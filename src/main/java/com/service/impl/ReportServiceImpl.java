package com.service.impl;

import com.domain.*;
import com.repository.*;
import com.resourse.ReportRequest;
import com.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private PaddyRepository paddyRepository;
    
    @Autowired
    private PaddySaleRepository paddySaleRepository;
    
    @Autowired
    private PaddyThreshingRepository paddyThreshingRepository;
    
    @Autowired
    private RiceRepository riceRepository;
    
    @Autowired
    private RiceSaleRepository riceSaleRepository;

    @Override
    public List<Map<String, Object>> generateReport(ReportRequest request) {
        switch (request.getReportType()) {
            case "PADDY_THRESHING":
                return generatePaddyThreshingReport(request);
            case "PADDY_SALE":
                return generatePaddySaleReport(request);
            case "PADDY_STOCK":
                return generatePaddyStockReport(request);
            case "RICE_SALE":
                return generateRiceSaleReport(request);
            case "RICE_STOCK":
                return generateRiceStockReport(request);
            default:
                return new ArrayList<>();
        }
    }

    private List<Map<String, Object>> generatePaddyThreshingReport(ReportRequest request) {
        List<PaddyThreshing> data = paddyThreshingRepository.findAll().stream()
            .filter(t -> filterByDate(t.getDate(), request.getFromDate(), request.getToDate()))
            .filter(t -> filterByWarehouse(t.getWarehouse(), request.getWarehouse()))
            .filter(t -> filterByType(t.getPaddyType(), request.getItemType()))
            .collect(Collectors.toList());
        
        return data.stream().map(t -> {
            Map<String, Object> map = new HashMap<>();
            map.put("paddyType", t.getPaddyType());
            map.put("paddyQuantity", t.getPaddyQuantity());
            map.put("riceType", t.getRiceType());
            map.put("riceQuantity", t.getRiceQuantity());
            map.put("brokenRiceType", t.getBrokenRiceType());
            map.put("brokenRiceQuantity", t.getBrokenRiceQuantity());
            map.put("polishRiceType", t.getPolishRiceType());
            map.put("polishRiceQuantity", t.getPolishRiceQuantity());
            map.put("warehouse", t.getWarehouse());
            map.put("date", t.getDate());
            map.put("user", t.getUser());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> generatePaddySaleReport(ReportRequest request) {
        List<PaddySale> data = paddySaleRepository.findAll().stream()
            .filter(s -> filterByDate(s.getDate(), request.getFromDate(), request.getToDate()))
            .filter(s -> filterByWarehouse(s.getWarehouse(), request.getWarehouse()))
            .filter(s -> filterByType(s.getPaddyType(), request.getItemType()))
            .filter(s -> filterByCustomer(s.getCustomerName(), request.getCustomerOrSupplier()))
            .collect(Collectors.toList());
        
        return data.stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("paddyType", s.getPaddyType());
            map.put("quantity", s.getQuantity());
            map.put("pricePerKg", s.getPricePerKg());
            map.put("totalAmount", s.getTotalamount());
            map.put("customerName", s.getCustomerName());
            map.put("customerId", s.getCustomerId());
            map.put("mobileNumber", s.getMobileNumber());
            map.put("warehouse", s.getWarehouse());
            map.put("date", s.getDate());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> generatePaddyStockReport(ReportRequest request) {
        List<PaddyStock> data = paddyRepository.findAll().stream()
            .filter(s -> filterByDate(s.getDate(), request.getFromDate(), request.getToDate()))
            .filter(s -> filterByWarehouse(s.getWarehouse(), request.getWarehouse()))
            .filter(s -> filterByType(s.getPaddyType(), request.getItemType()))
            .filter(s -> filterByCustomer(s.getCustomerName(), request.getCustomerOrSupplier()))
            .collect(Collectors.toList());
        
        return data.stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("paddyType", s.getPaddyType());
            map.put("quantity", s.getQuantity());
            map.put("pricePerKg", s.getPricePerKg());
            map.put("totalAmount", s.getTotalamount());
            map.put("supplierName", s.getCustomerName());
            map.put("supplierId", s.getCustomerId());
            map.put("mobileNumber", s.getMobileNumber());
            map.put("warehouse", s.getWarehouse());
            map.put("date", s.getDate());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> generateRiceSaleReport(ReportRequest request) {
        List<RiceSale> data = riceSaleRepository.findAll().stream()
            .filter(s -> filterByDate(s.getDate(), request.getFromDate(), request.getToDate()))
            .filter(s -> filterByType(s.getRiceType(), request.getItemType()))
            .filter(s -> filterByCustomer(s.getCustomerName(), request.getCustomerOrSupplier()))
            .collect(Collectors.toList());
        
        return data.stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("riceType", s.getRiceType());
            map.put("quantity", s.getQuantity());
            map.put("pricePerKg", s.getPricePerKg());
            map.put("totalAmount", s.getTotalAmount());
            map.put("customerName", s.getCustomerName());
            map.put("customerId", s.getCustomerId());
            map.put("mobileNumber", s.getMobileNumber());
            map.put("date", s.getDate());
            return map;
        }).collect(Collectors.toList());
    }

    private List<Map<String, Object>> generateRiceStockReport(ReportRequest request) {
        List<RiceStock> data = riceRepository.findAll().stream()
            .filter(s -> filterByDate(s.getDate(), request.getFromDate(), request.getToDate()))
            .filter(s -> filterByType(s.getRiceType(), request.getItemType()))
            .filter(s -> filterByCustomer(s.getCustomerName(), request.getCustomerOrSupplier()))
            .collect(Collectors.toList());
        
        return data.stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("riceType", s.getRiceType());
            map.put("quantity", s.getQuantity());
            map.put("pricePerKg", s.getPricePerKg());
            map.put("totalAmount", s.getTotalAmount());
            map.put("supplierName", s.getCustomerName());
            map.put("supplierId", s.getCustomerId());
            map.put("mobileNumber", s.getMobileNumber());
            map.put("date", s.getDate());
            return map;
        }).collect(Collectors.toList());
    }

    private boolean filterByDate(String date, String fromDate, String toDate) {
        if (fromDate == null || toDate == null) return true;
        return date.compareTo(fromDate) >= 0 && date.compareTo(toDate) <= 0;
    }

    private boolean filterByWarehouse(String warehouse, String filterWarehouse) {
        if (filterWarehouse == null || filterWarehouse.equals("ALL")) return true;
        return warehouse != null && warehouse.equals(filterWarehouse);
    }

    private boolean filterByType(String type, String filterType) {
        if (filterType == null || filterType.equals("ALL")) return true;
        return type != null && type.equals(filterType);
    }

    private boolean filterByCustomer(String customer, String filterCustomer) {
        if (filterCustomer == null || filterCustomer.equals("ALL")) return true;
        return customer != null && customer.equals(filterCustomer);
    }

    @Override
    public List<String> getWarehouses() {
        return paddyRepository.findDistinctWarehouses();
    }

    @Override
    public List<String> getPaddyTypes() {
        return paddyRepository.findDistinctPaddyTypes();
    }

    @Override
    public List<String> getRiceTypes() {
        return riceRepository.findDistinctRiceTypes();
    }

    @Override
    public List<String> getCustomers() {
        List<String> customers = new ArrayList<>();
        customers.addAll(paddySaleRepository.findDistinctCustomers());
        customers.addAll(riceSaleRepository.findDistinctCustomers());
        return customers.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> getSuppliers() {
        List<String> suppliers = new ArrayList<>();
        suppliers.addAll(paddyRepository.findDistinctSuppliers());
        suppliers.addAll(riceRepository.findDistinctSuppliers());
        return suppliers.stream().distinct().collect(Collectors.toList());
    }
}

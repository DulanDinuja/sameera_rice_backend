package com.service.impl;

import com.domain.PaddySale;
import com.domain.PaddyStock;
import com.domain.PaddyThreshing;
import com.domain.RiceStock;
import com.repository.PaddyRepository;
import com.repository.PaddySaleRepository;
import com.repository.PaddyThreshingRepository;
import com.repository.RiceRepository;
import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;
import com.service.PaddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaddyServiceImpl implements PaddyService {

    @Autowired
    private PaddyRepository paddyRepository;

    @Autowired
    private PaddySaleRepository paddySaleRepository;

    @Autowired
    private PaddyThreshingRepository paddyThreshingRepository;

    @Autowired
    private RiceRepository riceRepository;

    @Override
    public String addPaddyStock(PaddyStockRequest request) {
        try {
            PaddyStock paddyStock = new PaddyStock();
            paddyStock.setPaddyType(request.getPaddyType());
            paddyStock.setQuantity(request.getQuantity());
            paddyStock.setBags(request.getBags());
            paddyStock.setMoistureLevel(request.getMoistureLevel());
            paddyStock.setPricePerKg(request.getPricePerKg());
            paddyStock.setCustomerName(request.getCustomerName());
            paddyStock.setCustomerId(request.getCustomerId());
            paddyStock.setMobileNumber(request.getMobileNumber());
            paddyStock.setWarehouse(request.getWarehouse());
            paddyStock.setStatus(request.getStatus());
            paddyStock.setTotalamount(request.getTotalamount());
            paddyStock.setDate(request.getDate());
            paddyStock.setUser(request.getUser());
            
            paddyRepository.save(paddyStock);
            return "Paddy stock added successfully";
        } catch (Exception e) {
            return "Failed to add paddy stock: " + e.getMessage();
        }
    }

    @Override
    public String addPaddySale(PaddysaleRequest request) {
        try {
            PaddySale paddySale = new PaddySale();
            paddySale.setPaddyType(request.getPaddyType());
            paddySale.setQuantity(request.getQuantity());
            paddySale.setBags(request.getBags());
            paddySale.setPricePerKg(request.getPricePerKg());
            paddySale.setCustomerName(request.getCustomerName());
            paddySale.setCustomerId(request.getCustomerId());
            paddySale.setMobileNumber(request.getMobileNumber());
            paddySale.setWarehouse(request.getWarehouse());
            paddySale.setStatus(request.getStatus());
            paddySale.setTotalamount(request.getTotalamount());
            paddySale.setDate(request.getDate());
            paddySale.setUser(request.getUser());
            
            paddySaleRepository.save(paddySale);
            return "Paddy sale added successfully";
        } catch (Exception e) {
            return "Failed to add paddy sale: " + e.getMessage();
        }
    }

    @Override
    @Transactional
    public String addPaddyThreshing(PaddyThreshingRequest request) {
        try {
            // Deduct paddy from paddy_stock
            Optional<PaddyStock> paddyStockOpt = paddyRepository.findFirstByPaddyTypeOrderByIdDesc(request.getPaddyType());
            if (paddyStockOpt.isPresent()) {
                PaddyStock paddyStock = paddyStockOpt.get();
                if (paddyStock.getQuantity() < request.getPaddyQuantity()) {
                    return "Insufficient paddy stock";
                }
                paddyStock.setQuantity(paddyStock.getQuantity() - request.getPaddyQuantity());
                paddyRepository.save(paddyStock);
            } else {
                return "Paddy stock not found";
            }

            // Add rice to rice_stock
            addOrUpdateRiceStock(request.getRiceType(), request.getRiceQuantity(), request.getDate(), request.getUser());
            
            // Add broken rice to rice_stock
            addOrUpdateRiceStock(request.getBrokenRiceType(), request.getBrokenRiceQuantity(), request.getDate(), request.getUser());
            
            // Add polish rice to rice_stock
            addOrUpdateRiceStock(request.getPolishRiceType(), request.getPolishRiceQuantity(), request.getDate(), request.getUser());

            // Save threshing record
            PaddyThreshing paddyThreshing = new PaddyThreshing();
            paddyThreshing.setPaddyType(request.getPaddyType());
            paddyThreshing.setPaddyQuantity(request.getPaddyQuantity());
            paddyThreshing.setRiceType(request.getRiceType());
            paddyThreshing.setRiceQuantity(request.getRiceQuantity());
            paddyThreshing.setBrokenRiceType(request.getBrokenRiceType());
            paddyThreshing.setBrokenRiceQuantity(request.getBrokenRiceQuantity());
            paddyThreshing.setPolishRiceType(request.getPolishRiceType());
            paddyThreshing.setPolishRiceQuantity(request.getPolishRiceQuantity());
            paddyThreshing.setWarehouse(request.getWarehouse());
            paddyThreshing.setNotes(request.getNotes());
            paddyThreshing.setStatus(request.getStatus());
            paddyThreshing.setDate(request.getDate());
            paddyThreshing.setUser(request.getUser());
            paddyThreshingRepository.save(paddyThreshing);
            
            return "Paddy threshing added successfully";
        } catch (Exception e) {
            return "Failed to add paddy threshing: " + e.getMessage();
        }
    }

    private void addOrUpdateRiceStock(String riceType, int quantity, String date, String user) {
        Optional<RiceStock> riceStockOpt = riceRepository.findFirstByRiceTypeOrderByIdDesc(riceType);
        if (riceStockOpt.isPresent()) {
            RiceStock riceStock = riceStockOpt.get();
            riceStock.setQuantity(riceStock.getQuantity() + quantity);
            riceRepository.save(riceStock);
        } else {
            RiceStock newRiceStock = new RiceStock();
            newRiceStock.setRiceType(riceType);
            newRiceStock.setQuantity(quantity);
            newRiceStock.setPricePerKg(0.0);
            newRiceStock.setCustomerName("Threshing");
            newRiceStock.setCustomerId("N/A");
            newRiceStock.setMobileNumber("N/A");
            newRiceStock.setStatus("In Stock");
            newRiceStock.setDate(date);
            newRiceStock.setUser(user);
            riceRepository.save(newRiceStock);
        }
    }
}

package com.service.impl;

import com.domain.RiceSale;
import com.domain.RiceStock;
import com.repository.RiceSaleRepository;
import com.repository.RiceRepository;
import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;
import com.service.RiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RiceServiceImpl implements RiceService {

    @Autowired
    private RiceRepository riceRepository;

    @Autowired
    private RiceSaleRepository riceSaleRepository;

    @Override
    public String addRicestock(RiceStockRequest request) {
        try {
            RiceStock riceStock = new RiceStock();
            riceStock.setRiceType(request.getRiceType());
            riceStock.setQuantity(request.getQuantity());
            riceStock.setPricePerKg(request.getPricePerKg());
            riceStock.setCustomerName(request.getCustomerName());
            riceStock.setCustomerId(request.getCustomerId());
            riceStock.setMobileNumber(request.getMobileNumber());
            riceStock.setBags(request.getBags());
            riceStock.setStatus(request.getStatus());
            riceStock.setTotalamount(request.getTotalamount());
            riceStock.setDate(request.getDate());
            riceStock.setUser(request.getUser());
            
            riceRepository.save(riceStock);
            return "Rice stock added successfully";
        } catch (Exception e) {
            return "Failed to add rice stock: " + e.getMessage();
        }
    }

    @Override
    public String addRiceSale(RiceSaleRequest request) {
        try {
            RiceSale riceSale = new RiceSale();
            riceSale.setRiceType(request.getRiceType());
            riceSale.setQuantity(request.getQuantity());
            riceSale.setPricePerKg(request.getPricePerKg());
            riceSale.setCustomerName(request.getCustomerName());
            riceSale.setCustomerId(request.getCustomerId());
            riceSale.setMobileNumber(request.getMobileNumber());
            riceSale.setBags(request.getBags());
            riceSale.setTotalamount(request.getTotalamount());
            riceSale.setDate(request.getDate());
            riceSale.setUser(request.getUser());
            
            riceSaleRepository.save(riceSale);
            return "Rice sale added successfully";
        } catch (Exception e) {
            return "Failed to add rice sale: " + e.getMessage();
        }
    }
}

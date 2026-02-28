package com.service.impl;

import com.domain.RiceSale;
import com.domain.RiceStock;
import com.domain.RiceStockBackup;
import com.domain.RiceSaleBackup;
import com.repository.RiceSaleRepository;
import com.repository.RiceRepository;
import com.repository.RiceStockBackupRepository;
import com.repository.RiceSaleBackupRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private RiceStockBackupRepository riceStockBackupRepository;

    @Autowired
    private RiceSaleBackupRepository riceSaleBackupRepository;

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
            riceStock.setStatus("addstock");
            riceStock.setNote(null);
            riceStock.setTotalAmount(request.getTotalamount());
            riceStock.setDate(request.getDate());
            riceStock.setUser(request.getUser());
            
            riceRepository.save(riceStock);
            return "Rice stock added successfully";
        } catch (Exception e) {
            return "Failed to add rice stock: " + e.getMessage();
        }
    }

    @Override
    public String updateRiceStock(Long id, RiceStockRequest request) {
        try {
            Optional<RiceStock> riceStockOpt = riceRepository.findById(id);
            if (!riceStockOpt.isPresent()) {
                return "Rice stock not found";
            }
            RiceStock riceStock = riceStockOpt.get();
            
            // Backup before update
            RiceStockBackup backup = new RiceStockBackup();
            backup.setOriginalId(riceStock.getId());
            backup.setRiceType(riceStock.getRiceType());
            backup.setQuantity(riceStock.getQuantity());
            backup.setPricePerKg(riceStock.getPricePerKg());
            backup.setCustomerName(riceStock.getCustomerName());
            backup.setCustomerId(riceStock.getCustomerId());
            backup.setMobileNumber(riceStock.getMobileNumber());
            backup.setBags(riceStock.getBags());
            backup.setStatus(riceStock.getStatus());
            backup.setNote(riceStock.getNote());
            backup.setTotalAmount(riceStock.getTotalAmount());
            backup.setDate(riceStock.getDate());
            backup.setUser(riceStock.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("UPDATE");
            riceStockBackupRepository.save(backup);
            
            riceStock.setRiceType(request.getRiceType());
            riceStock.setQuantity(request.getQuantity());
            riceStock.setPricePerKg(request.getPricePerKg());
            riceStock.setCustomerName(request.getCustomerName());
            riceStock.setCustomerId(request.getCustomerId());
            riceStock.setMobileNumber(request.getMobileNumber());
            riceStock.setBags(request.getBags());
            riceStock.setStatus("U-addstock");
            riceStock.setNote(request.getNote());
            riceStock.setTotalAmount(request.getTotalamount());
            riceStock.setDate(request.getDate());
            riceStock.setUser(request.getUser());
            
            riceRepository.save(riceStock);
            return "Rice stock updated successfully";
        } catch (Exception e) {
            return "Failed to update rice stock: " + e.getMessage();
        }
    }

    @Override
    public String deleteRiceStock(Long id) {
        try {
            Optional<RiceStock> riceStockOpt = riceRepository.findById(id);
            if (!riceStockOpt.isPresent()) {
                return "Rice stock not found";
            }
            RiceStock riceStock = riceStockOpt.get();
            
            // Backup before delete
            RiceStockBackup backup = new RiceStockBackup();
            backup.setOriginalId(riceStock.getId());
            backup.setRiceType(riceStock.getRiceType());
            backup.setQuantity(riceStock.getQuantity());
            backup.setPricePerKg(riceStock.getPricePerKg());
            backup.setCustomerName(riceStock.getCustomerName());
            backup.setCustomerId(riceStock.getCustomerId());
            backup.setMobileNumber(riceStock.getMobileNumber());
            backup.setBags(riceStock.getBags());
            backup.setStatus(riceStock.getStatus());
            backup.setNote(riceStock.getNote());
            backup.setTotalAmount(riceStock.getTotalAmount());
            backup.setDate(riceStock.getDate());
            backup.setUser(riceStock.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("DELETE");
            riceStockBackupRepository.save(backup);
            
            riceRepository.deleteById(id);
            return "Rice stock deleted successfully";
        } catch (Exception e) {
            return "Failed to delete rice stock: " + e.getMessage();
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
            riceSale.setStatus(request.getStatus());
            riceSale.setNote(null);
            riceSale.setTotalAmount(request.getTotalamount());
            riceSale.setDate(request.getDate());
            riceSale.setUser(request.getUser());
            
            riceSaleRepository.save(riceSale);
            return "Rice sale added successfully";
        } catch (Exception e) {
            return "Failed to add rice sale: " + e.getMessage();
        }
    }

    @Override
    public String updateRiceSale(Long id, RiceSaleRequest request) {
        try {
            Optional<RiceSale> riceSaleOpt = riceSaleRepository.findById(id);
            if (!riceSaleOpt.isPresent()) {
                return "Rice sale not found";
            }
            RiceSale riceSale = riceSaleOpt.get();
            
            // Backup before update
            RiceSaleBackup backup = new RiceSaleBackup();
            backup.setOriginalId(riceSale.getId());
            backup.setRiceType(riceSale.getRiceType());
            backup.setQuantity(riceSale.getQuantity());
            backup.setPricePerKg(riceSale.getPricePerKg());
            backup.setCustomerName(riceSale.getCustomerName());
            backup.setCustomerId(riceSale.getCustomerId());
            backup.setMobileNumber(riceSale.getMobileNumber());
            backup.setBags(riceSale.getBags());
            backup.setStatus(riceSale.getStatus());
            backup.setNote(riceSale.getNote());
            backup.setTotalAmount(riceSale.getTotalAmount());
            backup.setDate(riceSale.getDate());
            backup.setUser(riceSale.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("UPDATE");
            riceSaleBackupRepository.save(backup);
            
            riceSale.setRiceType(request.getRiceType());
            riceSale.setQuantity(request.getQuantity());
            riceSale.setPricePerKg(request.getPricePerKg());
            riceSale.setCustomerName(request.getCustomerName());
            riceSale.setCustomerId(request.getCustomerId());
            riceSale.setMobileNumber(request.getMobileNumber());
            riceSale.setBags(request.getBags());
            riceSale.setStatus(request.getStatus());
            riceSale.setNote(request.getNote());
            riceSale.setTotalAmount(request.getTotalamount());
            riceSale.setDate(request.getDate());
            riceSale.setUser(request.getUser());
            
            riceSaleRepository.save(riceSale);
            return "Rice sale updated successfully";
        } catch (Exception e) {
            return "Failed to update rice sale: " + e.getMessage();
        }
    }

    @Override
    public String deleteRiceSale(Long id) {
        try {
            Optional<RiceSale> riceSaleOpt = riceSaleRepository.findById(id);
            if (!riceSaleOpt.isPresent()) {
                return "Rice sale not found";
            }
            RiceSale riceSale = riceSaleOpt.get();
            
            // Backup before delete
            RiceSaleBackup backup = new RiceSaleBackup();
            backup.setOriginalId(riceSale.getId());
            backup.setRiceType(riceSale.getRiceType());
            backup.setQuantity(riceSale.getQuantity());
            backup.setPricePerKg(riceSale.getPricePerKg());
            backup.setCustomerName(riceSale.getCustomerName());
            backup.setCustomerId(riceSale.getCustomerId());
            backup.setMobileNumber(riceSale.getMobileNumber());
            backup.setBags(riceSale.getBags());
            backup.setStatus(riceSale.getStatus());
            backup.setNote(riceSale.getNote());
            backup.setTotalAmount(riceSale.getTotalAmount());
            backup.setDate(riceSale.getDate());
            backup.setUser(riceSale.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("DELETE");
            riceSaleBackupRepository.save(backup);
            
            riceSaleRepository.deleteById(id);
            return "Rice sale deleted successfully";
        } catch (Exception e) {
            return "Failed to delete rice sale: " + e.getMessage();
        }
    }

    @Override
    public List<RiceStock> getAllRiceStock() {
        return riceRepository.findAll();
    }

    @Override
    public RiceStock getRiceStockById(Long id) {
        return riceRepository.findById(id).orElse(null);
    }

    @Override
    public List<RiceSale> getAllRiceSales() {
        return riceSaleRepository.findAll();
    }

    @Override
    public RiceSale getRiceSaleById(Long id) {
        return riceSaleRepository.findById(id).orElse(null);
    }
}

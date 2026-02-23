package com.service.impl;

import com.domain.PaddySale;
import com.domain.PaddyStock;
import com.domain.PaddyThreshing;
import com.domain.RiceStock;
import com.domain.PaddyStockBackup;
import com.domain.PaddySaleBackup;
import com.domain.PaddyThreshingBackup;
import com.repository.PaddyRepository;
import com.repository.PaddySaleRepository;
import com.repository.PaddyThreshingRepository;
import com.repository.RiceRepository;
import com.repository.PaddyStockBackupRepository;
import com.repository.PaddySaleBackupRepository;
import com.repository.PaddyThreshingBackupRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    private PaddyStockBackupRepository paddyStockBackupRepository;

    @Autowired
    private PaddySaleBackupRepository paddySaleBackupRepository;

    @Autowired
    private PaddyThreshingBackupRepository paddyThreshingBackupRepository;

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
    public String updatePaddyStock(Long id, PaddyStockRequest request) {
        try {
            Optional<PaddyStock> paddyStockOpt = paddyRepository.findById(id);
            if (!paddyStockOpt.isPresent()) {
                return "Paddy stock not found";
            }
            PaddyStock paddyStock = paddyStockOpt.get();
            
            // Backup before update
            PaddyStockBackup backup = new PaddyStockBackup();
            backup.setOriginalId(paddyStock.getId());
            backup.setPaddyType(paddyStock.getPaddyType());
            backup.setQuantity(paddyStock.getQuantity());
            backup.setBags(paddyStock.getBags());
            backup.setMoistureLevel(paddyStock.getMoistureLevel());
            backup.setPricePerKg(paddyStock.getPricePerKg());
            backup.setCustomerName(paddyStock.getCustomerName());
            backup.setCustomerId(paddyStock.getCustomerId());
            backup.setMobileNumber(paddyStock.getMobileNumber());
            backup.setWarehouse(paddyStock.getWarehouse());
            backup.setStatus(paddyStock.getStatus());
            backup.setTotalamount(paddyStock.getTotalamount());
            backup.setDate(paddyStock.getDate());
            backup.setUser(paddyStock.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("UPDATE");
            paddyStockBackupRepository.save(backup);
            
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
            return "Paddy stock updated successfully";
        } catch (Exception e) {
            return "Failed to update paddy stock: " + e.getMessage();
        }
    }

    @Override
    public String deletePaddyStock(Long id) {
        try {
            Optional<PaddyStock> paddyStockOpt = paddyRepository.findById(id);
            if (!paddyStockOpt.isPresent()) {
                return "Paddy stock not found";
            }
            PaddyStock paddyStock = paddyStockOpt.get();
            
            // Backup before delete
            PaddyStockBackup backup = new PaddyStockBackup();
            backup.setOriginalId(paddyStock.getId());
            backup.setPaddyType(paddyStock.getPaddyType());
            backup.setQuantity(paddyStock.getQuantity());
            backup.setBags(paddyStock.getBags());
            backup.setMoistureLevel(paddyStock.getMoistureLevel());
            backup.setPricePerKg(paddyStock.getPricePerKg());
            backup.setCustomerName(paddyStock.getCustomerName());
            backup.setCustomerId(paddyStock.getCustomerId());
            backup.setMobileNumber(paddyStock.getMobileNumber());
            backup.setWarehouse(paddyStock.getWarehouse());
            backup.setStatus(paddyStock.getStatus());
            backup.setTotalamount(paddyStock.getTotalamount());
            backup.setDate(paddyStock.getDate());
            backup.setUser(paddyStock.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("DELETE");
            paddyStockBackupRepository.save(backup);
            
            paddyRepository.deleteById(id);
            return "Paddy stock deleted successfully";
        } catch (Exception e) {
            return "Failed to delete paddy stock: " + e.getMessage();
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
    public String updatePaddySale(Long id, PaddysaleRequest request) {
        try {
            Optional<PaddySale> paddySaleOpt = paddySaleRepository.findById(id);
            if (!paddySaleOpt.isPresent()) {
                return "Paddy sale not found";
            }
            PaddySale paddySale = paddySaleOpt.get();
            
            // Backup before update
            PaddySaleBackup backup = new PaddySaleBackup();
            backup.setOriginalId(paddySale.getId());
            backup.setPaddyType(paddySale.getPaddyType());
            backup.setQuantity(paddySale.getQuantity());
            backup.setBags(paddySale.getBags());
            backup.setPricePerKg(paddySale.getPricePerKg());
            backup.setCustomerName(paddySale.getCustomerName());
            backup.setCustomerId(paddySale.getCustomerId());
            backup.setMobileNumber(paddySale.getMobileNumber());
            backup.setWarehouse(paddySale.getWarehouse());
            backup.setStatus(paddySale.getStatus());
            backup.setTotalamount(paddySale.getTotalamount());
            backup.setDate(paddySale.getDate());
            backup.setUser(paddySale.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("UPDATE");
            paddySaleBackupRepository.save(backup);
            
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
            return "Paddy sale updated successfully";
        } catch (Exception e) {
            return "Failed to update paddy sale: " + e.getMessage();
        }
    }

    @Override
    public String deletePaddySale(Long id) {
        try {
            Optional<PaddySale> paddySaleOpt = paddySaleRepository.findById(id);
            if (!paddySaleOpt.isPresent()) {
                return "Paddy sale not found";
            }
            PaddySale paddySale = paddySaleOpt.get();
            
            // Backup before delete
            PaddySaleBackup backup = new PaddySaleBackup();
            backup.setOriginalId(paddySale.getId());
            backup.setPaddyType(paddySale.getPaddyType());
            backup.setQuantity(paddySale.getQuantity());
            backup.setBags(paddySale.getBags());
            backup.setPricePerKg(paddySale.getPricePerKg());
            backup.setCustomerName(paddySale.getCustomerName());
            backup.setCustomerId(paddySale.getCustomerId());
            backup.setMobileNumber(paddySale.getMobileNumber());
            backup.setWarehouse(paddySale.getWarehouse());
            backup.setStatus(paddySale.getStatus());
            backup.setTotalamount(paddySale.getTotalamount());
            backup.setDate(paddySale.getDate());
            backup.setUser(paddySale.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("DELETE");
            paddySaleBackupRepository.save(backup);
            
            paddySaleRepository.deleteById(id);
            return "Paddy sale deleted successfully";
        } catch (Exception e) {
            return "Failed to delete paddy sale: " + e.getMessage();
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

    @Override
    public String updatePaddyThreshing(Long id, PaddyThreshingRequest request) {
        try {
            Optional<PaddyThreshing> paddyThreshingOpt = paddyThreshingRepository.findById(id);
            if (!paddyThreshingOpt.isPresent()) {
                return "Paddy threshing not found";
            }
            PaddyThreshing paddyThreshing = paddyThreshingOpt.get();
            
            // Backup before update
            PaddyThreshingBackup backup = new PaddyThreshingBackup();
            backup.setOriginalId(paddyThreshing.getId());
            backup.setPaddyType(paddyThreshing.getPaddyType());
            backup.setPaddyQuantity(paddyThreshing.getPaddyQuantity());
            backup.setRiceType(paddyThreshing.getRiceType());
            backup.setRiceQuantity(paddyThreshing.getRiceQuantity());
            backup.setBrokenRiceType(paddyThreshing.getBrokenRiceType());
            backup.setBrokenRiceQuantity(paddyThreshing.getBrokenRiceQuantity());
            backup.setPolishRiceType(paddyThreshing.getPolishRiceType());
            backup.setPolishRiceQuantity(paddyThreshing.getPolishRiceQuantity());
            backup.setWarehouse(paddyThreshing.getWarehouse());
            backup.setNotes(paddyThreshing.getNotes());
            backup.setStatus(paddyThreshing.getStatus());
            backup.setDate(paddyThreshing.getDate());
            backup.setUser(paddyThreshing.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("UPDATE");
            paddyThreshingBackupRepository.save(backup);
            
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
            return "Paddy threshing updated successfully";
        } catch (Exception e) {
            return "Failed to update paddy threshing: " + e.getMessage();
        }
    }

    @Override
    public String deletePaddyThreshing(Long id) {
        try {
            Optional<PaddyThreshing> paddyThreshingOpt = paddyThreshingRepository.findById(id);
            if (!paddyThreshingOpt.isPresent()) {
                return "Paddy threshing not found";
            }
            PaddyThreshing paddyThreshing = paddyThreshingOpt.get();
            
            // Backup before delete
            PaddyThreshingBackup backup = new PaddyThreshingBackup();
            backup.setOriginalId(paddyThreshing.getId());
            backup.setPaddyType(paddyThreshing.getPaddyType());
            backup.setPaddyQuantity(paddyThreshing.getPaddyQuantity());
            backup.setRiceType(paddyThreshing.getRiceType());
            backup.setRiceQuantity(paddyThreshing.getRiceQuantity());
            backup.setBrokenRiceType(paddyThreshing.getBrokenRiceType());
            backup.setBrokenRiceQuantity(paddyThreshing.getBrokenRiceQuantity());
            backup.setPolishRiceType(paddyThreshing.getPolishRiceType());
            backup.setPolishRiceQuantity(paddyThreshing.getPolishRiceQuantity());
            backup.setWarehouse(paddyThreshing.getWarehouse());
            backup.setNotes(paddyThreshing.getNotes());
            backup.setStatus(paddyThreshing.getStatus());
            backup.setDate(paddyThreshing.getDate());
            backup.setUser(paddyThreshing.getUser());
            backup.setBackupDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            backup.setOperation("DELETE");
            paddyThreshingBackupRepository.save(backup);
            
            paddyThreshingRepository.deleteById(id);
            return "Paddy threshing deleted successfully";
        } catch (Exception e) {
            return "Failed to delete paddy threshing: " + e.getMessage();
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

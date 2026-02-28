package com.service;

import com.domain.RiceSale;
import com.domain.RiceStock;
import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;

import java.util.List;

public interface RiceService {
    String addRicestock(RiceStockRequest riceStockRequest);
    String updateRiceStock(Long id, RiceStockRequest request);
    String deleteRiceStock(Long id, String deleteReason);
    List<RiceStock> getAllRiceStock();
    RiceStock getRiceStockById(Long id);
    String addRiceSale(RiceSaleRequest riceSaleRequest);
    String updateRiceSale(Long id, RiceSaleRequest request);
    String deleteRiceSale(Long id, String deleteReason);
    List<RiceSale> getAllRiceSales();
    RiceSale getRiceSaleById(Long id);
}

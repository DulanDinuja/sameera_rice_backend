package com.service;

import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;

public interface RiceService {
    String addRicestock(RiceStockRequest riceStockRequest);
    String updateRiceStock(Long id, RiceStockRequest request);
    String deleteRiceStock(Long id);
    String addRiceSale(RiceSaleRequest riceSaleRequest);
    String updateRiceSale(Long id, RiceSaleRequest request);
    String deleteRiceSale(Long id);
}

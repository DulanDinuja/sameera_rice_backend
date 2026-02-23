package com.service;

import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;

public interface RiceService {
    String addRicestock(RiceStockRequest riceStockRequest);
    String addRiceSale(RiceSaleRequest riceSaleRequest);
}

package com.service;

import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;

public interface PaddyService {
    String addPaddyStock(PaddyStockRequest request);
    String updatePaddyStock(Long id, PaddyStockRequest request);
    String deletePaddyStock(Long id);
    String addPaddySale(PaddysaleRequest request);
    String updatePaddySale(Long id, PaddysaleRequest request);
    String deletePaddySale(Long id);
    String addPaddyThreshing(PaddyThreshingRequest request);
    String updatePaddyThreshing(Long id, PaddyThreshingRequest request);
    String deletePaddyThreshing(Long id);
}

package com.service;

import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;

public interface PaddyService {
    String addPaddyStock(PaddyStockRequest request);
    String addPaddySale(PaddysaleRequest request);
    String addPaddyThreshing(PaddyThreshingRequest request);
}

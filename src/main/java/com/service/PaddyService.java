package com.service;

import com.domain.PaddySale;
import com.domain.PaddyStock;
import com.domain.PaddyThreshing;
import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;

import java.util.List;

public interface PaddyService {
    String addPaddyStock(PaddyStockRequest request);
    String updatePaddyStock(Long id, PaddyStockRequest request);
    String deletePaddyStock(Long id, String deleteReason);
    List<PaddyStock> getAllPaddyStock();
    PaddyStock getPaddyStockById(Long id);
    String addPaddySale(PaddysaleRequest request);
    String updatePaddySale(Long id, PaddysaleRequest request);
    String deletePaddySale(Long id, String deleteReason);
    List<PaddySale> getAllPaddySale();
    PaddySale getPaddySaleById(Long id);
    String addPaddyThreshing(PaddyThreshingRequest request);
    String updatePaddyThreshing(Long id, PaddyThreshingRequest request);
    String deletePaddyThreshing(Long id, String deleteReason);
    List<PaddyThreshing> getAllPaddyThreshing();
    PaddyThreshing getPaddyThreshingById(Long id);
}

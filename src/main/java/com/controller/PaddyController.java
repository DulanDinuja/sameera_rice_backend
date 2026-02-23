package com.controller;

import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;
import com.service.PaddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/paddy")
@CrossOrigin(origins = "*")
public class PaddyController {
    @Autowired
    private PaddyService paddyService;
    
    @PostMapping("/addstock")
    public ResponseEntity<String> addStock(@RequestBody PaddyStockRequest request) {
        String result = paddyService.addPaddyStock(request);
        if (result.equals("Paddy stock added successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/paddysale")
    public ResponseEntity<String> addSale(@RequestBody PaddysaleRequest request) {
        String result = paddyService.addPaddySale(request);
        if (result.equals("Paddy sale added successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/paddythreshing")
    public ResponseEntity<String> addThreshing(@RequestBody PaddyThreshingRequest request) {
        String result = paddyService.addPaddyThreshing(request);
        if (result.equals("Paddy threshing added successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

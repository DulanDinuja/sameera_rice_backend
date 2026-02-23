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

    @PutMapping("/addstock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody PaddyStockRequest request) {
        String result = paddyService.updatePaddyStock(id, request);
        if (result.equals("Paddy stock updated successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/addstock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        String result = paddyService.deletePaddyStock(id);
        if (result.equals("Paddy stock deleted successfully")) {
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

    @PutMapping("/paddysale/{id}")
    public ResponseEntity<String> updateSale(@PathVariable Long id, @RequestBody PaddysaleRequest request) {
        String result = paddyService.updatePaddySale(id, request);
        if (result.equals("Paddy sale updated successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/paddysale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        String result = paddyService.deletePaddySale(id);
        if (result.equals("Paddy sale deleted successfully")) {
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

    @PutMapping("/paddythreshing/{id}")
    public ResponseEntity<String> updateThreshing(@PathVariable Long id, @RequestBody PaddyThreshingRequest request) {
        String result = paddyService.updatePaddyThreshing(id, request);
        if (result.equals("Paddy threshing updated successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/paddythreshing/{id}")
    public ResponseEntity<String> deleteThreshing(@PathVariable Long id) {
        String result = paddyService.deletePaddyThreshing(id);
        if (result.equals("Paddy threshing deleted successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }
}

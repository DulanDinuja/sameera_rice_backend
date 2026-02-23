package com.controller;

import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;
import com.service.RiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rice")
@CrossOrigin(origins = "*")
public class RiceController {

    @Autowired
    private RiceService riceService;
    @PostMapping("/addstock")
    public ResponseEntity<String> addStock(@RequestBody RiceStockRequest request){
        try {
            String result = riceService.addRicestock(request);
            if (result.equals("Rice stock added successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/addstock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody RiceStockRequest request) {
        try {
            String result = riceService.updateRiceStock(id, request);
            if (result.equals("Rice stock updated successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/addstock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id) {
        try {
            String result = riceService.deleteRiceStock(id);
            if (result.equals("Rice stock deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/ricesale")
    public ResponseEntity<String> addSale(@RequestBody RiceSaleRequest request){
        try {
            String result = riceService.addRiceSale(request);
            if (result.equals("Rice sale added successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/ricesale/{id}")
    public ResponseEntity<String> updateSale(@PathVariable Long id, @RequestBody RiceSaleRequest request) {
        try {
            String result = riceService.updateRiceSale(id, request);
            if (result.equals("Rice sale updated successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/ricesale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id) {
        try {
            String result = riceService.deleteRiceSale(id);
            if (result.equals("Rice sale deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}

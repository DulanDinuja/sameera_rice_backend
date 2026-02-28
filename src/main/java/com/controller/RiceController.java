package com.controller;

import com.domain.RiceSale;
import com.domain.RiceStock;
import com.resourse.DeleteRequest;
import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;
import com.service.RiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rice")
@CrossOrigin(origins = "*")
public class RiceController {

    @Autowired
    private RiceService riceService;
    @GetMapping("/addstock")
    public ResponseEntity<?> getAllRiceStock() {
        try {
            List<RiceStock> stocks = riceService.getAllRiceStock();
            return ResponseEntity.ok(stocks);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/addstock/{id}")
    public ResponseEntity<RiceStock> getRiceStockById(@PathVariable Long id) {
        try {
            RiceStock stock = riceService.getRiceStockById(id);
            if (stock != null) {
                return ResponseEntity.ok(stock);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

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
    public ResponseEntity<String> deleteStock(@PathVariable Long id, @RequestBody(required = false) DeleteRequest request) {
        try {
            String deleteReason = (request != null && request.getDeleteReason() != null) ? request.getDeleteReason() : "";
            String result = riceService.deleteRiceStock(id, deleteReason);
            if (result.equals("Rice stock deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/ricesale")
    public ResponseEntity<List<RiceSale>> getAllRiceSales() {
        try {
            List<RiceSale> sales = riceService.getAllRiceSales();
            return ResponseEntity.ok(sales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/ricesale/{id}")
    public ResponseEntity<RiceSale> getRiceSaleById(@PathVariable Long id) {
        try {
            RiceSale sale = riceService.getRiceSaleById(id);
            if (sale != null) {
                return ResponseEntity.ok(sale);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
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
    public ResponseEntity<String> deleteSale(@PathVariable Long id, @RequestBody(required = false) DeleteRequest request) {
        try {
            String deleteReason = (request != null && request.getDeleteReason() != null) ? request.getDeleteReason() : "";
            String result = riceService.deleteRiceSale(id, deleteReason);
            if (result.equals("Rice sale deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

}

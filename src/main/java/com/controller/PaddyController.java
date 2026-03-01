package com.controller;

import com.domain.PaddyStock;
import com.domain.PaddySale;
import com.domain.PaddyThreshing;
import com.resourse.PaddyStockRequest;
import com.resourse.PaddyThreshingRequest;
import com.resourse.PaddysaleRequest;
import com.service.PaddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paddy")
@CrossOrigin(origins = "*")
public class PaddyController {
    @Autowired
    private PaddyService paddyService;
    
    @GetMapping("/addstock")
    public ResponseEntity<List<PaddyStock>> getAllPaddyStock() {
        try {
            List<PaddyStock> stocks = paddyService.getAllPaddyStock();
            return ResponseEntity.ok(stocks);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/addstock/{id}")
    public ResponseEntity<PaddyStock> getPaddyStockById(@PathVariable Long id) {
        try {
            PaddyStock stock = paddyService.getPaddyStockById(id);
            if (stock != null) {
                return ResponseEntity.ok(stock);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @PostMapping("/addstock")
    public ResponseEntity<String> addStock(@RequestBody PaddyStockRequest request) {
        try {
            String result = paddyService.addPaddyStock(request);
            if (result.equals("Paddy stock added successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/addstock/{id}")
    public ResponseEntity<String> updateStock(@PathVariable Long id, @RequestBody PaddyStockRequest request) {
        try {
            String result = paddyService.updatePaddyStock(id, request);
            if (result.equals("Paddy stock updated successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/addstock/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable Long id, @RequestParam String deleteReason) {
        try {
            String result = paddyService.deletePaddyStock(id, deleteReason);
            if (result.equals("Paddy stock deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/paddysale")
    public ResponseEntity<List<PaddySale>> getAllPaddySale() {
        try {
            List<PaddySale> sales = paddyService.getAllPaddySale();
            return ResponseEntity.ok(sales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/paddysale/{id}")
    public ResponseEntity<PaddySale> getPaddySaleById(@PathVariable Long id) {
        try {
            PaddySale sale = paddyService.getPaddySaleById(id);
            if (sale != null) {
                return ResponseEntity.ok(sale);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/paddysale")
    public ResponseEntity<String> addSale(@RequestBody PaddysaleRequest request) {
        try {
            String result = paddyService.addPaddySale(request);
            if (result.equals("Paddy sale added successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/paddysale/{id}")
    public ResponseEntity<String> updateSale(@PathVariable Long id, @RequestBody PaddysaleRequest request) {
        try {
            String result = paddyService.updatePaddySale(id, request);
            if (result.equals("Paddy sale updated successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/paddysale/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable Long id, @RequestParam String deleteReason) {
        try {
            String result = paddyService.deletePaddySale(id, deleteReason);
            if (result.equals("Paddy sale deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/paddythreshing")
    public ResponseEntity<List<PaddyThreshing>> getAllPaddyThreshing() {
        try {
            List<PaddyThreshing> threshings = paddyService.getAllPaddyThreshing();
            return ResponseEntity.ok(threshings);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/paddythreshing/{id}")
    public ResponseEntity<PaddyThreshing> getPaddyThreshingById(@PathVariable Long id) {
        try {
            PaddyThreshing threshing = paddyService.getPaddyThreshingById(id);
            if (threshing != null) {
                return ResponseEntity.ok(threshing);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/paddythreshing")
    public ResponseEntity<String> addThreshing(@RequestBody PaddyThreshingRequest request) {
        try {
            String result = paddyService.addPaddyThreshing(request);
            if (result.equals("Paddy threshing added successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/paddythreshing/{id}")
    public ResponseEntity<String> updateThreshing(@PathVariable Long id, @RequestBody PaddyThreshingRequest request) {
        try {
            String result = paddyService.updatePaddyThreshing(id, request);
            if (result.equals("Paddy threshing updated successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/paddythreshing/{id}")
    public ResponseEntity<String> deleteThreshing(@PathVariable Long id, @RequestParam String deleteReason) {
        try {
            String result = paddyService.deletePaddyThreshing(id, deleteReason);
            if (result.equals("Paddy threshing deleted successfully")) {
                return ResponseEntity.ok(result);
            }
            return ResponseEntity.badRequest().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}

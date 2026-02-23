package com.controller;

import com.resourse.RiceSaleRequest;
import com.resourse.RiceStockRequest;
import com.service.RiceService;
import org.springframework.beans.factory.annotation.Autowired;
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
        String result = riceService.addRicestock(request);
        if (result.equals("Rice stock added successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
        
    }

    @PostMapping("/ricesale")
    public ResponseEntity<String> addSale(@RequestBody RiceSaleRequest request){
        String result = riceService.addRiceSale(request);
        if (result.equals("Rice sale added successfully")) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

}

package com.resourse;

import lombok.Data;

@Data
public class PaddyStockRequest {
    private String paddyType;
    private int quantity;
    private int bags;
    private double moistureLevel;
    private double pricePerKg;
    private String customerName;
    private String customerId;
    private String mobileNumber;
    private String warehouse;
    private String status;
    private String note;
    private double totalamount;
    private String date;
    private String user;
}


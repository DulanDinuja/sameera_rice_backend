package com.resourse;

import lombok.Data;

@Data
public class RiceSaleRequest {
    private String riceType;
    private int quantity;
    private double pricePerKg;
    private String customerName;
    private String customerId;
    private String mobileNumber;
    private Integer bags;
    private String status;
    private String note;
    private double totalamount;
    private String date;
    private String user;
}

package com.resourse;

import lombok.Data;

@Data
public class PaddysaleRequest {
    private String paddyType;
    private int quantity;
    private int bags;
    private double pricePerKg;
    private String customerName;
    private String customerId;
    private String mobileNumber;
    private String warehouse;
    private String status;
    private double totalamount;
    private String date;
    private String user;
}

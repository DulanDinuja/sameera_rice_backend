package com.resourse;

import lombok.Data;

@Data
public class PaddyThreshingRequest {
    private String paddyType;
    private int PaddyQuantity;
    private String riceType;
    private int riceQuantity;
    private String brokenRiceType;
    private int brokenRiceQuantity;
    private String polishRiceType;
    private int polishRiceQuantity;
    private String warehouse;
    private String notes;
    private String status;
    private String date;
    private String user;
}

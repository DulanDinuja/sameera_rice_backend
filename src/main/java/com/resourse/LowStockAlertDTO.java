package com.resourse;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LowStockAlertDTO {
    private String itemName;
    private int quantity;
    private String alertLevel;
}

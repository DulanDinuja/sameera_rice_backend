package com.resourse;

import lombok.Data;

@Data
public class ReportRequest {
    private String fromDate;
    private String toDate;
    private String reportType;
    private String warehouse;
    private String itemType;
    private String customerOrSupplier;
}

package com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rice_stock_backup")
@Data
public class RiceStockBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long originalId;
    @Column
    private String riceType;
    @Column
    private int quantity;
    @Column
    private double pricePerKg;
    @Column
    private String customerName;
    @Column
    private String customerId;
    @Column
    private String mobileNumber;
    @Column
    private Integer bags;
    @Column
    private String status;
    @Column
    private String note;
    @Column
    private double totalAmount;
    @Column
    private String date;
    @Column
    private String user;
    @Column
    private String backupDate;
    @Column
    private String operation;
}

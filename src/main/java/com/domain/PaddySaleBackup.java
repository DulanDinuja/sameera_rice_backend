package com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paddy_sale_backup")
@Data
public class PaddySaleBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long originalId;
    @Column
    private String paddyType;
    @Column
    private int quantity;
    @Column
    private int bags;
    @Column
    private double pricePerKg;
    @Column
    private String customerName;
    @Column
    private String customerId;
    @Column
    private String mobileNumber;
    @Column
    private String warehouse;
    @Column
    private String status;
    @Column
    private double totalamount;
    @Column
    private String date;
    @Column
    private String user;
    @Column
    private String backupDate;
    @Column
    private String operation;
}

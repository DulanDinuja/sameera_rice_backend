package com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paddy_sale")
@Data
public class PaddySale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paddyType;
    @Column(nullable = false)
    private int quantity;
    @Column
    private Number bags;
    @Column(nullable = false)
    private double pricePerKg;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private String mobileNumber;
    @Column
    private String warehouse;
    @Column
    private String status;
    @Column
    private double totalamount;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String user;
}

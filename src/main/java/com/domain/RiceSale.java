package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rice_sale")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiceSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String riceType;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double pricePerKg;
    @Column(nullable = false)
    private String customerName;
    @Column(nullable = false)
    private String customerId;
    @Column(nullable = false)
    private String mobileNumber;
    @Column
    private Integer bags;
    @Column
    private String status;
    @Column
    private String note;
    @Column
    private double totalAmount;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String user;
}

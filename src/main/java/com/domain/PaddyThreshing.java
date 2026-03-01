package com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paddy_threshing")
@Data
public class PaddyThreshing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paddyType;
    @Column(nullable = false)
    private int PaddyQuantity;
    @Column(nullable = false)
    private String riceType;
    @Column(nullable = false)
    private int riceQuantity;
    @Column(nullable = false)
    private String brokenRiceType;
    @Column(nullable = false)
    private int brokenRiceQuantity;
    @Column(nullable = false)
    private String polishRiceType;
    @Column(nullable = false)
    private int polishRiceQuantity;
    @Column
    private String warehouse;
    @Column
    private String note;
    @Column
    private String status;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String user;
}


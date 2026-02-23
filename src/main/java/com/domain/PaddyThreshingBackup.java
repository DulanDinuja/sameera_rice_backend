package com.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "paddy_threshing_backup")
@Data
public class PaddyThreshingBackup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long originalId;
    @Column
    private String paddyType;
    @Column
    private int PaddyQuantity;
    @Column
    private String riceType;
    @Column
    private int riceQuantity;
    @Column
    private String brokenRiceType;
    @Column
    private int brokenRiceQuantity;
    @Column
    private String polishRiceType;
    @Column
    private int polishRiceQuantity;
    @Column
    private String warehouse;
    @Column
    private String notes;
    @Column
    private String status;
    @Column
    private String date;
    @Column
    private String user;
    @Column
    private String backupDate;
    @Column
    private String operation;
}

package com.repository;

import com.domain.RiceSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RiceSaleRepository extends JpaRepository<RiceSale, Long> {
    @Query("SELECT SUM(r.totalAmount) FROM RiceSale r")
    Double getTotalRevenue();
    
    @Query("SELECT SUM(r.totalAmount) FROM RiceSale r WHERE FUNCTION('MONTH', FUNCTION('STR_TO_DATE', r.date, '%Y-%m-%d')) = :month AND FUNCTION('YEAR', FUNCTION('STR_TO_DATE', r.date, '%Y-%m-%d')) = :year")
    Double getRevenueByMonth(@Param("month") int month, @Param("year") int year);
    
    List<RiceSale> findTop3ByOrderByIdDesc();
    
    @Query("SELECT DISTINCT r.customerName FROM RiceSale r")
    List<String> findDistinctCustomers();
}

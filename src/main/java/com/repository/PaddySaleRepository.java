package com.repository;

import com.domain.PaddySale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaddySaleRepository extends JpaRepository<PaddySale, Long> {
    @Query("SELECT SUM(p.totalamount) FROM PaddySale p")
    Double getTotalRevenue();
    
    @Query("SELECT SUM(p.totalamount) FROM PaddySale p WHERE FUNCTION('MONTH', FUNCTION('STR_TO_DATE', p.date, '%Y-%m-%d')) = :month AND FUNCTION('YEAR', FUNCTION('STR_TO_DATE', p.date, '%Y-%m-%d')) = :year")
    Double getRevenueByMonth(@Param("month") int month, @Param("year") int year);
    
    List<PaddySale> findTop3ByOrderByIdDesc();
    
    @Query("SELECT DISTINCT p.customerName FROM PaddySale p")
    List<String> findDistinctCustomers();
}

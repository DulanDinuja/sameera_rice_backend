package com.repository;

import com.domain.RiceStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RiceRepository extends JpaRepository<RiceStock, Long> {
    @Query("SELECT r FROM RiceStock r WHERE r.riceType = :riceType ORDER BY r.id DESC LIMIT 1")
    Optional<RiceStock> findFirstByRiceTypeOrderByIdDesc(@Param("riceType") String riceType);
    
    @Query("SELECT SUM(r.quantity) FROM RiceStock r")
    Integer getTotalRiceStock();
    
    @Query("SELECT SUM(r.quantity) FROM RiceStock r WHERE FUNCTION('MONTH', FUNCTION('STR_TO_DATE', r.date, '%Y-%m-%d')) = :month AND FUNCTION('YEAR', FUNCTION('STR_TO_DATE', r.date, '%Y-%m-%d')) = :year")
    Integer getRiceStockByMonth(@Param("month") int month, @Param("year") int year);
    
    List<RiceStock> findTop3ByOrderByIdDesc();
    
    @Query("SELECT r FROM RiceStock r WHERE r.quantity < :threshold ORDER BY r.quantity ASC")
    List<RiceStock> findLowStockItems(@Param("threshold") int threshold);
    
    @Query("SELECT DISTINCT r.riceType FROM RiceStock r")
    List<String> findDistinctRiceTypes();
    
    @Query("SELECT DISTINCT r.customerName FROM RiceStock r")
    List<String> findDistinctSuppliers();
}

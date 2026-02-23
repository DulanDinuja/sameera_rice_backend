package com.repository;

import com.domain.PaddyStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaddyRepository extends JpaRepository<PaddyStock, Long> {
    @Query("SELECT p FROM PaddyStock p WHERE p.paddyType = :paddyType ORDER BY p.id DESC LIMIT 1")
    Optional<PaddyStock> findFirstByPaddyTypeOrderByIdDesc(@Param("paddyType") String paddyType);
    
    @Query("SELECT SUM(p.quantity) FROM PaddyStock p")
    Integer getTotalPaddyStock();
    
    @Query("SELECT SUM(p.quantity) FROM PaddyStock p WHERE FUNCTION('MONTH', FUNCTION('STR_TO_DATE', p.date, '%Y-%m-%d')) = :month AND FUNCTION('YEAR', FUNCTION('STR_TO_DATE', p.date, '%Y-%m-%d')) = :year")
    Integer getPaddyStockByMonth(@Param("month") int month, @Param("year") int year);
    
    @Query("SELECT COUNT(DISTINCT p.warehouse) FROM PaddyStock p WHERE p.warehouse IS NOT NULL")
    Long countDistinctWarehouses();
    
    List<PaddyStock> findTop3ByOrderByIdDesc();
    
    @Query("SELECT p FROM PaddyStock p WHERE p.quantity < :threshold ORDER BY p.quantity ASC")
    List<PaddyStock> findLowStockItems(@Param("threshold") int threshold);
    
    @Query("SELECT DISTINCT p.warehouse FROM PaddyStock p WHERE p.warehouse IS NOT NULL")
    List<String> findDistinctWarehouses();
    
    @Query("SELECT DISTINCT p.paddyType FROM PaddyStock p")
    List<String> findDistinctPaddyTypes();
    
    @Query("SELECT DISTINCT p.customerName FROM PaddyStock p")
    List<String> findDistinctSuppliers();
}

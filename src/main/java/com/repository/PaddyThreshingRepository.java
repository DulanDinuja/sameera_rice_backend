package com.repository;

import com.domain.PaddyThreshing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaddyThreshingRepository extends JpaRepository<PaddyThreshing, Long> {
    List<PaddyThreshing> findTop3ByOrderByIdDesc();
}

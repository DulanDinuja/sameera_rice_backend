package com.repository;

import com.domain.PendingRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PendingRegistrationRepository extends JpaRepository<PendingRegistration, Long> {

    PendingRegistration findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByNic(String nic);

    void deleteByEmail(String email);

    // For cleanup of expired pending registrations
    List<PendingRegistration> findByVerificationExpiryBefore(LocalDateTime dateTime);

    void deleteByVerificationExpiryBefore(LocalDateTime dateTime);
}


package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, UUID> {

    boolean existsByTitle(String title);

    Optional<Bill> findByTitleContainingIgnoreCase(String title);

}

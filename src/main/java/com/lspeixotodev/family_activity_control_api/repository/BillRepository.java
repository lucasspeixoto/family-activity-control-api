package com.lspeixotodev.family_activity_control_api.repository;

import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {
}

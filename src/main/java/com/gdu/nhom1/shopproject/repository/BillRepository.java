package com.gdu.nhom1.shopproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdu.nhom1.shopproject.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    
}

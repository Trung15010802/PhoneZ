package com.gdu.nhom1.shopproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdu.nhom1.shopproject.models.Bill;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneNumberContainingIgnoreCase(
            String firstName, String lastName, String email, String phoneNumber);
}

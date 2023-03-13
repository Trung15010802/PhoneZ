package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import com.gdu.nhom1.shopproject.models.Bill;

public interface BillService {
    List<Bill> getAllBills();

    void addBill(Bill bill);

    void removeBillById(long id);

    Optional<Bill> getBillById(long id);

    List<Bill> search(String keyword);
}

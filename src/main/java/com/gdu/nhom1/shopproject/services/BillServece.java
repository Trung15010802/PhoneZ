package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.nhom1.shopproject.models.Bill;
import com.gdu.nhom1.shopproject.repository.BillRepository;

@Service
public class BillServece {
    @Autowired
    BillRepository billRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public void addBill(Bill bill) {
        billRepository.save(bill);
    }

    public void removeBillById(long id) {
        billRepository.deleteById(id);
    }

    public Optional<Bill> getBillById(long id) {
        return billRepository.findById(id);
    }
}

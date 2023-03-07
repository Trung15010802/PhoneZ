package com.gdu.nhom1.shopproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdu.nhom1.shopproject.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory_Id(int id);

    List<Product> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

}

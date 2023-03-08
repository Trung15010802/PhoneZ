package com.gdu.nhom1.shopproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gdu.nhom1.shopproject.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameContainingIgnoreCase(String name);
}

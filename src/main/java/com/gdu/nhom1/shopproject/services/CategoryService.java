package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import com.gdu.nhom1.shopproject.models.Category;

public interface CategoryService {
    List<Category> getAllCategory();
    void addCategory(Category category);
    void removeCategoryById(int id);
    Optional<Category> getCategoryById(int id);
    List<Category> search(String keyword);
}

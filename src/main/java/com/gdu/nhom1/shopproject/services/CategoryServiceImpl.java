package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.nhom1.shopproject.models.Category;
import com.gdu.nhom1.shopproject.repository.CategoryRepository;

@Service // Marks class as a Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void removeCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> search(String keyword) {
        return categoryRepository.findByNameContainingIgnoreCase(keyword);
    }
}

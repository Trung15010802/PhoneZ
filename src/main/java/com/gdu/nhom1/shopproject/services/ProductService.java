package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import com.gdu.nhom1.shopproject.models.Product;

public interface ProductService {
    List<Product> getAllProduct();

    void addProduct(Product product);

    void removeProductById(long id);

    Optional<Product> getProductById(long id);

    List<Product> getAllProductsByCategoryId(int id);

    List<Product> search(String keyword);
}

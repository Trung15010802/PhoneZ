package com.gdu.nhom1.shopproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.nhom1.shopproject.models.Product;
import com.gdu.nhom1.shopproject.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired // Tiêm phụ thuộc
    ProductRepository productRepository;

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void removeProductById(long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProductsByCategoryId(int id){
        return productRepository.findAllByCategory_Id(id);
    }

    public List<Product> search(String keyword) {
        return productRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

}

package com.gdu.nhom1.shopproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

// import com.gdu.nhom1.shopproject.global.GlobalData;
import com.gdu.nhom1.shopproject.models.Product;
import com.gdu.nhom1.shopproject.services.CategoryService;
import com.gdu.nhom1.shopproject.services.ProductService;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;

    @GetMapping({ "/", "/home" })
    public String home(Model model) {
        // model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        // model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        // model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }

    
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductDetail(Model model, @PathVariable int id) {
        model.addAttribute("product", productService.getProductById(id).get());
        // model.addAttribute("cartCount", GlobalData.cart.size());
        return "viewProduct";
       
    }

    @GetMapping("/shop/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Product> results = productService.search(keyword);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", results);
        // model.addAttribute("cartCount", GlobalData.cart.size());
        return "shop";
    }
}

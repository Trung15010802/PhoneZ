package com.gdu.nhom1.shopproject.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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
    public String home(Model model, HttpSession session) {
        session.setAttribute("session", session);
        return "index";
    }

    @GetMapping("/shop")
    public String shop(Model model, HttpSession session) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        session.setAttribute("session", session);
        return "shop";
    }

    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id, HttpSession session) {
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        session.setAttribute("session", session);
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProductDetail(Model model, @PathVariable int id, HttpSession session) {
        model.addAttribute("product", productService.getProductById(id).get());
        session.setAttribute("session", session);

        return "viewProduct";

    }

    @GetMapping("/shop/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Product> results = productService.search(keyword);
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", results);

        return "shop";
    }
}

package com.gdu.nhom1.shopproject.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gdu.nhom1.shopproject.dto.BillDTO;
import com.gdu.nhom1.shopproject.models.Bill;
import com.gdu.nhom1.shopproject.models.Product;
import com.gdu.nhom1.shopproject.services.BillServece;
import com.gdu.nhom1.shopproject.services.ProductService;

@Controller
@SessionAttributes("cart")
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    BillServece billServece;

    private List<Product> cart;

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity, HttpSession session,
            Model model) {
        Product product = productService.getProductById(productId).get();
        product.setQuantity(quantity);
        product.setPrice(quantity * product.getPrice());
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        int current = cart.size();
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == productId) {
                cart.get(i).setQuantity(quantity + cart.get(i).getQuantity());
                current++;
            }
        }
        if (current == cart.size()) {
            cart.add(product);
        }
        session.setAttribute("cartCount", cart.size());

        return "redirect:/shop";
    }

    @PostMapping("/updateCart")
    public String updateCart(@RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity, HttpSession session,
            Model model) {
        Product product = productService.getProductById(productId).get();

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getId() == productId) {
                if (quantity <= 0) { // quantity == 0
                    cart.remove(i);
                } else if (quantity > 0 && product.getQuantity() > quantity) {
                    cart.get(i).setQuantity(quantity);
                    cart.get(i).setPrice(quantity * product.getPrice());
                } else {
                    System.out.println("số lượng vượt quá số lượng sản phẩm có trong kho"); // cần 1 thông báo
                }

            }
        }

        session.setAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        return "/cart";
    }

    @GetMapping("/cart")
    public String cartGet(Model model, HttpSession session) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("cartCount", cart.size());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", cart);
        return "cart";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index, HttpSession session) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        cart = (List<Product>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("billDTO", new BillDTO());
        model.addAttribute("total", cart.stream().mapToDouble(Product::getPrice).sum());
        return "/checkout";
    }

    @PostMapping("/payNow")
    public String payNow(@ModelAttribute("billDTO") BillDTO billDTO, Model model, HttpSession session) {
        Bill bill = new Bill();
        bill.setId(billDTO.getId());
        bill.setFirstName(billDTO.getFirstName());
        bill.setLastName(billDTO.getLastName());
        bill.setAddress(billDTO.getAddress());
        bill.setPhoneNumber(billDTO.getPhoneNumber());
        bill.setEmail(billDTO.getEmail());
        bill.setAddInformation(billDTO.getAddInformation());

        double totalMoney = 0;
        List<Product> productList = new ArrayList<Product>();
        for (int i = 0; i < cart.size(); i++) {
            long id = cart.get(i).getId();
            Product product = productService.getProductById(id).get();
            productList.add(product);

            totalMoney += cart.get(i).getPrice();
        }
        // for (String productName : billDTO.getProduct_name()) {
        // Product product = productService.get;
        // if (product != null) {
        // productList.add(product);
        // totalMoney += product.getPrice();
        // }
        // }
        // List<Product> productList = new ArrayList<>();
        // for (String productName : billDTO.getProduct_name()) {
        // Product product = productService.getProductByName(productName);
        // if (product != null) {
        // productList.add(product);
        // totalMoney += product.getPrice();
        // }
        // }
        // bill.setProducts(productList);

        bill.setProducts(productList);
        bill.setPrice(totalMoney);
        billServece.addBill(bill);
        cart.clear();
        model.addAttribute("result", bill.getId());
        model.addAttribute("parameters", productList);

        return "/orderPlaced";
    }

}

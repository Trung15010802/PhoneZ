package com.gdu.nhom1.shopproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gdu.nhom1.shopproject.dto.UserRegistrationDTO;
import com.gdu.nhom1.shopproject.services.UserService;

@Controller
@RequestMapping("/register")
public class UserRegistrationController {
    private UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDTO registrationDTO, Model model) {

        try {
            
            if (userService.loadUserByUsername(registrationDTO.getEmail()) != null) {
                model.addAttribute("success", false);
                return "redirect:/register?error";
            } 
        } catch (Exception e) {
           System.out.println(e);
        }

            model.addAttribute("success", true);
            userService.addUser(registrationDTO);
            return "redirect:/register?success";
        
    }

}

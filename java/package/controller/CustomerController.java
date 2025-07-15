package com.example.CarRentalManagement.controller;

import com.example.CarRentalManagement.model.Customers;
import com.example.CarRentalManagement.service.CustomerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("customers")
    public List<Customers> getAllCustomers() {
        return customerService.getAllCustomer();
    }

    @GetMapping("/current-user")
    public Customers getCurrentCustomer(Principal principal) {
        return customerService.getCurrentCustomer(principal);
    }

    @PostMapping("/register")
    public void registerCustomer(@RequestParam("name") String name,
                                   @RequestParam("email") String email,
                                   @RequestParam("phoneNum") String phoneNum,
                                   @RequestParam("password") String password,
                                   HttpServletResponse response) throws IOException {
        boolean success = customerService.registerCustomer(name, email, phoneNum, password);
        if (success) {
            response.sendRedirect("/login.html?success=registered");
        }
        else {
            response.sendRedirect("/register.html?error=emailExists");
        }
    }
}

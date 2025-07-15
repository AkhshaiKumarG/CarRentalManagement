package com.example.CarRentalManagement.service;

import com.example.CarRentalManagement.model.Customers;
import com.example.CarRentalManagement.repository.CustomerRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public List<Customers> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        Customers customer = customerRepository.findByEmail(emailId);
        if (customer == null) {
            throw new UsernameNotFoundException("Invalid Credentials...");
        }
        return customer;
    }

    public boolean registerCustomer(String name, String email, String phoneNum, String password) {

        if (customerRepository.findByEmail(email) != null) {
            return false;
        }
        Customers customer = new Customers(name, email, phoneNum);
        customer.setPassword(passwordEncoder.encode(password));
        customerRepository.save(customer);
        return true;
    }

    public Customers getCurrentCustomer(Principal principal) {
        return customerRepository.findByEmail(principal.getName());
    }
}

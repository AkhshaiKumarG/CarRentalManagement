package com.example.CarRentalManagement.repository;

import com.example.CarRentalManagement.model.Customers;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {
  
    Customers findByEmail(String email);

    @EntityGraph(attributePaths = {"carBookings", "carBookings.car"})
    List<Customers> findAll();
}

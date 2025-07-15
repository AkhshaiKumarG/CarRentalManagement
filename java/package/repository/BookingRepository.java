package com.example.CarRentalManagement.repository;

import com.example.CarRentalManagement.model.Bookings;
import com.example.CarRentalManagement.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {
    List<Bookings> findByCustomers(Customers customer);
}

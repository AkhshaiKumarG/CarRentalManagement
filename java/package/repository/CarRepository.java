package com.example.CarRentalManagement.repository;

import com.example.CarRentalManagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM car WHERE brand = :brand")
    List<Car> findByBrand(String brand);
}

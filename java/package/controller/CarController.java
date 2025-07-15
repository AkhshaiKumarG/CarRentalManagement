package com.example.CarRentalManagement.controller;

import com.example.CarRentalManagement.model.Car;
import com.example.CarRentalManagement.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/dashboard")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/dashboard/available")
    public List<Car> getAllAvailableCars() {
        return carService.getAllAvailableCars();
    }

    @PostMapping("/addCar")
    public String addCar(@RequestParam("carName") String carName,
                         @RequestParam("brand") String brand,
                         @RequestParam("pricePerDay") int pricePerDay,
                         @RequestParam("imageUrl") String imageUrl) {
        return carService.addCar(carName, brand, pricePerDay, imageUrl);
    }

    @GetMapping("dashboard/filter")
    public List<Car> getCarByBrand(@Param("brand") String brand) {
        return carService.getCarByBrand(brand);
    }
}

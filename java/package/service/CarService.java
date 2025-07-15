package com.example.CarRentalManagement.service;

import com.example.CarRentalManagement.model.Car;
import com.example.CarRentalManagement.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public String addCar(String carName, String brand, int pricePerDay, String imageUrl) {
        Car newCar = new Car(carName, brand, pricePerDay, imageUrl);
        carRepository.save(newCar);
        return "Successfully added";
    }

    public List<Car> getAllAvailableCars() {
        List<Car> allCarsList = carRepository.findAll();
        List<Car> listOfAvailableCars = new ArrayList<>();
        for (Car car: allCarsList) {
            if (car.getAvailability().equals("Yes")) {
                listOfAvailableCars.add(car);
            }
        }
        return listOfAvailableCars;
    }

    public List<Car> getCarByBrand(String brand) {
        return carRepository.findByBrand(brand);
    }
}

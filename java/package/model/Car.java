package com.example.CarRentalManagement.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int car_id;
    private String carName;
    private String brand;
    private int pricePerDay;
    private String availability = "Yes";
    private String imageUrl;

    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinTable(
            name = "car_customer",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customers> carBookedByCustomers;

    public int getId() {
        return car_id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Car() {
    }

    public Car(String carName, String brand, int pricePerDay, String imageUrl) {
        this.pricePerDay = pricePerDay;
        this.brand = brand;
        this.carName = carName;
        this.imageUrl = imageUrl;
    }
}

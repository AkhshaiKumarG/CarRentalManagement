package com.example.CarRentalManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;

@Entity
public class Bookings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String customerName;
    private String phoneNum;
    private int car_id;
    private int numOfDays;
    private double totalAmount;
    private String emailId;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private String status = "upcoming";

    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST
            , CascadeType.REFRESH})
    private Car car;
    @ManyToOne (cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JsonBackReference
    private Customers customers;

    public long getId() {
        return id;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumOfDays() {
        return numOfDays;
    }

    public void setNumOfDays(int numOfDays) {
        this.numOfDays = numOfDays;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Bookings() {
    }

    public Bookings(String customerName, String phoneNum, int car_id, int numOfDays, double totalAmount, String emailId, Car car, Customers customers) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.car_id = car_id;
        this.numOfDays = numOfDays;
        this.totalAmount = totalAmount;
        this.emailId = emailId;
        this.car = car;
        this.customers = customers;
    }
}

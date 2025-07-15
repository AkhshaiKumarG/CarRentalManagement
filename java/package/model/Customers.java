package com.example.CarRentalManagement.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.print.Book;
import java.util.Collection;
import java.util.List;

@Entity
public class Customers implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;
    private String name;
    private String email;
    private String phoneNum;
    private String role = "USER";
    private String password;

    @ManyToMany (cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinTable(
            name = "car_customer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id")
    )
    private List<Car> carsBooked;

    @OneToMany(mappedBy = "customers",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<Bookings> carBookings;
    @JsonManagedReference
    public List<Bookings> getCarBookings() {
        return carBookings;
    }

    public void setCarBookings(List<Bookings> carBookings) {
        this.carBookings = carBookings;
    }

    public String getName() {
        return name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Car> getCarsBooked() {
        return carsBooked;
    }

    public void setCarsBooked(List<Car> carsBooked) {
        this.carsBooked = carsBooked;
    }

    public Customers() {
    }

    public Customers(String name, String email, String phoneNum) {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }
}

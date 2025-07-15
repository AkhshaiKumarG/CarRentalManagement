package com.example.CarRentalManagement.controller;

import com.example.CarRentalManagement.model.Bookings;
import com.example.CarRentalManagement.model.Customers;
import com.example.CarRentalManagement.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.*;


@RestController
public class BookingsController {
    @Autowired
    BookingService bookingService;

    @GetMapping("all-Customer-Bookings")
    public List<Customers> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/my-bookings")
    public List<Bookings> getMyBookings(Principal principal) {
        return bookingService.getMyBookings(principal.getName());
    }

    @PostMapping("/bookCar")
    public String bookCar(@RequestParam("car_id") int car_id,
                          @RequestParam("customerName") String customerName,
                          @RequestParam("phoneNum") String phoneNum,
                          @RequestParam("emailId") String emailId,
                          @RequestParam("pickupDate") LocalDate pickupDate,
                          @RequestParam("returnDate") LocalDate returnDate){
        try {
        return bookingService.bookCar(car_id, customerName, phoneNum, emailId, pickupDate, returnDate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("bookings/cancel/{bookingId}")
    public String cancelBooking(@PathVariable("bookingId") int bookingId) {
        return bookingService.cancelBooking(bookingId);
    }
}

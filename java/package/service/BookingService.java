package com.example.CarRentalManagement.service;

import com.example.CarRentalManagement.model.Bookings;
import com.example.CarRentalManagement.model.Car;
import com.example.CarRentalManagement.model.Customers;
import com.example.CarRentalManagement.repository.BookingRepository;
import com.example.CarRentalManagement.repository.CarRepository;
import com.example.CarRentalManagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.*;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    CustomerRepository customerRepository;

//    public String bookCar(Bookings bookings) {
    public String bookCar(int car_id, String customerName, String phoneNum, String emailId, LocalDate pickupDate, LocalDate returnDate) {
        Car carById = carRepository.findById(car_id).orElse(null);
        Customers customers = customerRepository.findByEmail(emailId);

        Bookings bookings = new Bookings();
        if (carById != null && carById.getAvailability().equals("Yes")) {
            carById.setAvailability("No");
            bookings.setCar_id(car_id);
            bookings.setCar(carById);
            bookings.setCustomers(customers);
            bookings.setCustomerName(customerName);
            bookings.setPhoneNum(phoneNum);
            bookings.setEmailId(emailId);
            bookings.setPickupDate(pickupDate);
            bookings.setReturnDate(returnDate);

            if (customers.getCarsBooked() == null) {
                customers.setCarsBooked(new ArrayList<>());
            }
            if (!customers.getCarsBooked().contains(carById)) {
                customers.getCarsBooked().add(carById);
            }
            long days = ChronoUnit.DAYS.between(pickupDate, returnDate);
            bookings.setNumOfDays((int)days);
            bookings.setTotalAmount(bookings.getNumOfDays() * carById.getPricePerDay());
            customerRepository.save(customers);
            bookingRepository.save(bookings);
            return "Successfully Booked a Car";
        }
        else {
            return "Car not found";
        }
    }

    public List<Customers> getAllBookings() {
        return customerRepository.findAll();
    }

    public String cancelBooking(long bookingId) {
        Bookings booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            Car car = booking.getCar();
            if ("cancelled".equalsIgnoreCase(booking.getStatus())) {
                return "Already cancelled...";
            }
            if (car != null) {
                car.setAvailability("Yes");
                carRepository.save(car);
            }
            booking.setStatus("cancelled");
            bookingRepository.save(booking);
            return "Successfully cancelled";
        }
        return "Booking Id Not Found...";
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateBookingStatuses() {
        List<Bookings> allBookings = bookingRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Bookings booking : allBookings) {
            if (booking.getReturnDate() != null && today.isAfter(booking.getReturnDate())) {
                booking.setStatus("Completed...");
            }
            else if (booking.getPickupDate() != null && today.isEqual(booking.getPickupDate())) {
                booking.setStatus("In-progress...");
            }
            else if (booking.getPickupDate() != null && today.isBefore(booking.getPickupDate())){
                booking.setStatus("Upcoming...");
            }
            bookingRepository.save(booking);
        }
    }

    public List<Bookings> getMyBookings(String email) {
        Customers customer = customerRepository.findByEmail(email);
        if (customer != null) {
            return bookingRepository.findByCustomers(customer);
        }
        return new ArrayList<>();
    }
}

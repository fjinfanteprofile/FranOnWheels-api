package com.example.demo.service;

import com.example.demo.model.domain.Bookings;

import java.util.List;
import java.util.Optional;

public interface BookingsService {

    Bookings createBooking(Bookings booking);

    List<Bookings> getAllBookings();

    Optional<Bookings> getBookingById(Long id);

    Bookings updateBooking(Bookings booking);

    void deleteBookingById(Long id);
}

package com.example.demo.service;

import com.example.demo.model.domain.Bookings;
import com.example.demo.repository.BookingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsService {

    private final BookingsRepository bookingsRepository;

    @Autowired
    public BookingsService(BookingsRepository bookingsRepository) {
        this.bookingsRepository = bookingsRepository;
    }

    // Create operation
    public Bookings createBooking(Bookings booking) {
        return bookingsRepository.save(booking);
    }

    // Read operations
    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    public Optional<Bookings> getBookingById(Long id) {
        return bookingsRepository.findById(id);
    }

    // Update operation
    public Bookings updateBooking(Bookings booking) {
        return bookingsRepository.save(booking);
    }

    // Delete operation
    public void deleteBookingById(Long id) {
        bookingsRepository.deleteById(id);
    }
}

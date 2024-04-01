package com.example.demo.service;

import com.example.demo.model.Bookings;
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

    public List<Bookings> getAllBookings() {
        return bookingsRepository.findAll();
    }

    public Optional<Bookings> getBookingsById(Long id) {
        return bookingsRepository.findById(id);
    }

    public Bookings saveBookings(Bookings bookings) {
        return bookingsRepository.save(bookings);
    }

    public void deleteBookings(Long id) {
        bookingsRepository.deleteById(id);
    }

    // Add more methods as needed
}

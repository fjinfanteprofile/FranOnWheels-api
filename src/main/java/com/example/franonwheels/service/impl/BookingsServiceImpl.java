package com.example.franonwheels.service.impl;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.repository.BookingsRepository;
import com.example.franonwheels.service.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

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

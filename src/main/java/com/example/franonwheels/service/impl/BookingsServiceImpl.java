package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.BookingsMapper;
import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.dtos.BookingsDTO;
import com.example.franonwheels.repository.BookingsRepository;
import com.example.franonwheels.service.BookingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingsServiceImpl implements BookingsService {

    private final BookingsRepository bookingsRepository;

    // Create operation
    public BookingsDTO createBooking(BookingsDTO bookingDTO) {
        return BookingsMapper.BookingstoDTO(bookingsRepository.save(BookingsMapper.BookingsDTOtoEntity(bookingDTO)));
    }

    // Read operations
    public List<BookingsDTO> getAllBookings() {

        List<Bookings> bookings = bookingsRepository.findAll();

        return bookings.stream()
                .map(BookingsMapper::BookingstoDTO)
                .collect(Collectors.toList());

    }

    public Optional<BookingsDTO> getBookingById(Long id) {
        Optional<Bookings> booking = bookingsRepository.findById(id);
        return booking.map(BookingsMapper::BookingstoDTO);
    }

    // Update operation
    public BookingsDTO updateBooking(BookingsDTO bookingsDTO) {
        return BookingsMapper.BookingstoDTO(this.bookingsRepository.save(BookingsMapper.BookingsDTOtoEntity(bookingsDTO)));
    }

    // Delete operation
    public void deleteBookingById(Long id) {
        bookingsRepository.deleteById(id);
    }

    // Activate operation
    public void activateBookingById(Long id) {
        Optional<Bookings> optionalBooking = bookingsRepository.findById(id);
        optionalBooking.ifPresent(booking -> {
            booking.setActive(1);
            bookingsRepository.save(booking);
        });
    }

    // Deactivate operation
    public void deactivateBookingById(Long id) {
        Optional<Bookings> optionalBooking = bookingsRepository.findById(id);
        optionalBooking.ifPresent(booking -> {
            booking.setActive(0);
            bookingsRepository.save(booking);
        });
    }

    // Show active bookings
    public List<BookingsDTO> getActiveBookings() {
        List<Bookings> activeBookings = bookingsRepository.findByActive(1);
        return activeBookings.stream()
                .map(BookingsMapper::BookingstoDTO)
                .collect(Collectors.toList());
    }

    // Show inactive bookings
    public List<BookingsDTO> getInactiveBookings() {
        List<Bookings> inactiveBookings = bookingsRepository.findByActive(0);
        return inactiveBookings.stream()
                .map(BookingsMapper::BookingstoDTO)
                .collect(Collectors.toList());
    }
}

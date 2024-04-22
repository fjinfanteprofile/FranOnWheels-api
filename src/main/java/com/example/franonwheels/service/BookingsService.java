package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.dtos.BookingsDTO;

import java.util.List;
import java.util.Optional;

public interface BookingsService {

    BookingsDTO createBooking(BookingsDTO booking);

    List<BookingsDTO> getAllBookings();

    Optional<BookingsDTO> getBookingById(Long id);

    BookingsDTO updateBooking(BookingsDTO booking);

    void deleteBookingById(Long id);
}

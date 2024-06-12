package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Bookings;
import com.example.franonwheels.model.dtos.BookingsDTO;

import java.util.List;
import java.util.Optional;

public interface BookingsService {

    BookingsDTO createBooking(BookingsDTO booking);

    List<BookingsDTO> getAllBookings();

    Optional<BookingsDTO> getBookingById(Long id);

    Optional<BookingsDTO> updateBooking(BookingsDTO booking, Long id);

    void deleteBookingById(Long id);
}

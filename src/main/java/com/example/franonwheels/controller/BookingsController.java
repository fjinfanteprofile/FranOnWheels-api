package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.BookingsDTO;
import com.example.franonwheels.service.impl.BookingsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingsController {

    private final BookingsServiceImpl bookingsServiceImpl;

    @PostMapping
    public ResponseEntity<BookingsDTO> createBooking(@RequestBody BookingsDTO bookingDTO) {
        BookingsDTO createdBooking = bookingsServiceImpl.createBooking(bookingDTO);
        return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookingsDTO>> getAllBookings() {
        List<BookingsDTO> allBookings = bookingsServiceImpl.getAllBookings();
        return new ResponseEntity<>(allBookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingsDTO> getBookingById(@PathVariable Long id) {
        Optional<BookingsDTO> booking = bookingsServiceImpl.getBookingById(id);
        return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<BookingsDTO>> updateBooking(@RequestBody BookingsDTO bookingDTO, @PathVariable Long id) {
        Optional<BookingsDTO> updatedBooking = bookingsServiceImpl.updateBooking(bookingDTO,id);
        return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long id) {
        bookingsServiceImpl.deactivateBookingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateBookingById(@PathVariable Long id) {
        bookingsServiceImpl.activateBookingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<BookingsDTO>> getActiveBookings() {
        List<BookingsDTO> activeBookings = bookingsServiceImpl.getActiveBookings();
        return new ResponseEntity<>(activeBookings, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<BookingsDTO>> getInactiveBookings() {
        List<BookingsDTO> inactiveBookings = bookingsServiceImpl.getInactiveBookings();
        return new ResponseEntity<>(inactiveBookings, HttpStatus.OK);
    }
}

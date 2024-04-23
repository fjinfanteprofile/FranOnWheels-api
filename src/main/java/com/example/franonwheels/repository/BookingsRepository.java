package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    void deleteBookingsByUserId(Long userId);
}

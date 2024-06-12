package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

    void deleteBookingsByUserId(Long userId);

    List<Bookings> findByActive(int i);
}

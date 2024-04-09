package com.example.demo.repository;

import com.example.demo.model.domain.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings, Long> {

}

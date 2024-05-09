package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

    void deleteClassesByUserId(Long userId);

    List<Classes> findByActive(int i);

    List<Classes> findByDayOfWeekIgnoreCase(String day);

    List<Classes> findByDate(LocalDate date);

    List<Classes> findByUser(User user);
}

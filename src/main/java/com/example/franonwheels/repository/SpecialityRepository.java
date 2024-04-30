package com.example.franonwheels.repository;

import com.example.franonwheels.model.domain.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Long> {

    List<Speciality> findByActive(int i);
}
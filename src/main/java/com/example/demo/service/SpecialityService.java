package com.example.demo.service;

import com.example.demo.model.domain.Speciality;

import java.util.List;
import java.util.Optional;

public interface SpecialityService {

    Speciality createSpeciality(Speciality speciality);

    List<Speciality> getAllSpecialities();

    Optional<Speciality> getSpecialityById(Long id);

    Speciality updateSpeciality(Speciality speciality);

    void deleteSpecialityById(Long id);
}

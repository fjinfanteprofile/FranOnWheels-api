package com.example.franonwheels.service.impl;

import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.repository.SpecialityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl {

    private final SpecialityRepository specialityRepository;


    // Create operation
    public Speciality createSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    // Read operations
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    public Optional<Speciality> getSpecialityById(Long id) {
        return specialityRepository.findById(id);
    }

    // Update operation
    public Speciality updateSpeciality(Speciality speciality) {
        return specialityRepository.save(speciality);
    }

    // Delete operation
    public void deleteSpecialityById(Long id) {
        specialityRepository.deleteById(id);
    }
}

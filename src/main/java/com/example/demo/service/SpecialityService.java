package com.example.demo.service;

import com.example.demo.model.domain.Speciality;
import com.example.demo.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

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

package com.example.demo.service;

import com.example.demo.model.Speciality;
import com.example.demo.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SpecialityService {

    private final SpecialityRepository specialityRepository;

    @Autowired
    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }

    // Add more methods as needed
}

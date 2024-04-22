package com.example.franonwheels.service;

import com.example.franonwheels.model.dtos.SpecialityDTO;

import java.util.List;
import java.util.Optional;

public interface SpecialityService {

    SpecialityDTO createSpeciality(SpecialityDTO specialityDTO);

    List<SpecialityDTO> getAllSpecialities();

    Optional<SpecialityDTO> getSpecialityById(Long id);

    SpecialityDTO updateSpeciality(SpecialityDTO specialityDTO);

    void deleteSpecialityById(Long id);
}

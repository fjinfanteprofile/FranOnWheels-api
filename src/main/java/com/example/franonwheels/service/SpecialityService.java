package com.example.franonwheels.service;

import com.example.franonwheels.model.dtos.SpecialityDTO;

import java.util.List;
import java.util.Optional;

public interface SpecialityService {

    SpecialityDTO createSpeciality(SpecialityDTO specialityDTO);

    List<SpecialityDTO> getAllSpecialities();

    Optional<SpecialityDTO> getSpecialityById(Long id);

    Optional<SpecialityDTO> updateSpeciality(SpecialityDTO specialityDTO,Long id);

    void deleteSpecialityById(Long id);
}

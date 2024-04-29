package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.SpecialityMapper;
import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.model.dtos.SpecialityDTO;
import com.example.franonwheels.repository.SpecialityRepository;
import com.example.franonwheels.service.SpecialityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityDTO createSpeciality(SpecialityDTO specialityDTO) {
        return SpecialityMapper.SpecialitytoDTO(this.specialityRepository.save(SpecialityMapper.SpecialityDTOtoEntity(specialityDTO)));
    }

    public List<SpecialityDTO> getAllSpecialities() {
        List<Speciality> specialities = specialityRepository.findAll();
        return specialities.stream()
                .map(SpecialityMapper::SpecialitytoDTO)
                .collect(Collectors.toList());
    }

    public Optional<SpecialityDTO> getSpecialityById(Long id) {
        Optional<Speciality> speciality = specialityRepository.findById(id);
        return speciality.map(SpecialityMapper::SpecialitytoDTO);
    }

    public SpecialityDTO updateSpeciality(SpecialityDTO specialityDTO) {
        return SpecialityMapper.SpecialitytoDTO(this.specialityRepository.save(SpecialityMapper.SpecialityDTOtoEntity(specialityDTO)));
    }

    public void deleteSpecialityById(Long id) {
        specialityRepository.deleteById(id);
    }

    // Activate operation
    public void activateSpecialityById(Long id) {
        Optional<Speciality> optionalSpeciality = specialityRepository.findById(id);
        optionalSpeciality.ifPresent(spec -> {
            spec.setActive(1);
            specialityRepository.save(spec);
        });
    }

    // Deactivate operation
    public void deactivateSpecialityById(Long id) {
        Optional<Speciality> optionalSpeciality = specialityRepository.findById(id);
        optionalSpeciality.ifPresent(spec -> {
            spec.setActive(0);
            specialityRepository.save(spec);
        });
    }

    // Get active specialities
    public List<SpecialityDTO> getActiveSpecialities() {
        List<Speciality> activeSpecialities = specialityRepository.findByActive(1);
        return activeSpecialities.stream()
                .map(SpecialityMapper::SpecialitytoDTO)
                .collect(Collectors.toList());
    }

    // Get inactive specialities
    public List<SpecialityDTO> getInactiveSpecialities() {
        List<Speciality> inactiveSpecialities = specialityRepository.findByActive(0);
        return inactiveSpecialities.stream()
                .map(SpecialityMapper::SpecialitytoDTO)
                .collect(Collectors.toList());
    }
}

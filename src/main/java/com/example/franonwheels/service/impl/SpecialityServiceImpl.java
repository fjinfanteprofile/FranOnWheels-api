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
}

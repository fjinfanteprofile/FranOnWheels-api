package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.model.dtos.SpecialityDTO;

import java.util.NoSuchElementException;

public class SpecialityMapper {

    public static SpecialityDTO SpecialitytoDTO(Speciality speciality) {

        if (speciality == null){

            throw new NoSuchElementException("Speciality must not be null");

        }
        return SpecialityDTO.builder()
                .name(speciality.getName())
                .build();

    }

    public static Speciality SpecialityDTOtoEntity(SpecialityDTO specialityDTO) {

        return Speciality.builder()
                .name(specialityDTO.getName())
                .build();
    }


}

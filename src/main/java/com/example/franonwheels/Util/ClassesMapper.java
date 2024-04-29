package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.ClassesDTO;

public class ClassesMapper {

    public static ClassesDTO ClassestoDTO(Classes classes) {

        if (classes == null){

            return null;

        }

        return ClassesDTO.builder()
                .date(classes.getDate())
                .vehicleId(classes.getVehicle().getId())
                .timeEnd(classes.getTimeEnd())
                .timeStart(classes.getTimeStart())
                .active(classes.getActive())
                .build();
    }

    public static Classes ClassesDTOtoEntity(ClassesDTO classesDTO){

        if (classesDTO == null){

            return null;
        }

        return Classes.builder()
                .date(classesDTO.getDate())
                .vehicle(Vehicle.builder().id(classesDTO.getVehicleId()).build())
                .timeEnd(classesDTO.getTimeEnd())
                .timeStart(classesDTO.getTimeStart())
                .active(classesDTO.getActive())
                .build();

    }

}

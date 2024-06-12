package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Classes;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.ClassesDTO;

import java.util.Objects;

public class ClassesMapper {

    public static ClassesDTO ClassestoDTO(Classes classes) {
        if (classes == null){
            return null;
        }

        Long userId = classes.getUser() != null ? classes.getUser().getId() : null;

        return ClassesDTO.builder()
                .id(classes.getId())
                .vehicleId(classes.getVehicle().getId())
                .timeEnd(classes.getTimeEnd())
                .timeStart(classes.getTimeStart())
                .active(classes.getActive())
                .userId(userId)
                .dayOfWeek(classes.getDate().getDayOfWeek().toString())
                .date(classes.getDate())
                .userId(classes.getUser().getId())
                .build();
    }

    public static Classes ClassesDTOtoEntity(ClassesDTO classesDTO){

        if (classesDTO == null){
            return null;
        }

        return Classes.builder()
                .id(classesDTO.getId())
                .vehicle(Vehicle.builder().id(classesDTO.getVehicleId()).build())
                .timeEnd(classesDTO.getTimeEnd())
                .timeStart(classesDTO.getTimeStart())
                .active(classesDTO.getActive())
                .dayOfWeek(classesDTO.getDate().getDayOfWeek().toString())
                .date(classesDTO.getDate())
                .user(User.builder().id(classesDTO.getUserId()).build())
                .build();

    }

}

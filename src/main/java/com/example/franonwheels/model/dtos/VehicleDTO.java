package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleDTO {
    private VehicleTypeDTO type;
    private String model;
    private int year;
    private String licensePlate;
    private String gearbox;
    private int displacementCc;
}

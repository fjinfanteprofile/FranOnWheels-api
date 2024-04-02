package com.example.demo.model.dtos;

import com.example.demo.model.dtos.VehicleTypeDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VehicleDTO {
    private Long id;
    private VehicleTypeDTO type;
    private String model;
    private int year;
    private String licensePlate;
    private String gearbox;
    private int displacementCc;
}

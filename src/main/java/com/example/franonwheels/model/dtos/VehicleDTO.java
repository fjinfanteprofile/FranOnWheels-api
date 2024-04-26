package com.example.franonwheels.model.dtos;

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
    private Integer year;
    private String licensePlate;
    private String gearbox;
    private Integer displacementCc;
    private Integer active;
}

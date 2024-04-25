package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.VehicleDTO;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import lombok.experimental.UtilityClass;

import java.util.NoSuchElementException;

@UtilityClass
public class VehicleMapper {

    public VehicleDTO vehicleConvertToDTO(Vehicle vehicle){

        if (vehicle == null){
            throw new NoSuchElementException("Vehicle provided is null");
        }
        return VehicleDTO.builder()
                .id(vehicle.getId())
                .type(vehicleTypeConvertToDTO(vehicle.getType()))
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .licensePlate(vehicle.getLicensePlate())
                .gearbox(vehicle.getGearbox())
                .displacementCc(vehicle.getDisplacementCc())
                .build();
    }

    public Vehicle vehicleConvertToEntity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            throw new IllegalArgumentException("VehicleDTO provided is null");
        }

        return Vehicle.builder()
                .id(vehicleDTO.getId())
                .type(vehicleTypeConvertToEntity(vehicleDTO.getType()))
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .licensePlate(vehicleDTO.getLicensePlate())
                .gearbox(vehicleDTO.getGearbox())
                .displacementCc(vehicleDTO.getDisplacementCc())
                .build();
    }

    private VehicleTypeDTO vehicleTypeConvertToDTO(VehicleType vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        return VehicleTypeDTO.builder()
                .id(vehicleType.getId())
                .name(vehicleType.getName())
                .build();
    }

    private VehicleType vehicleTypeConvertToEntity(VehicleTypeDTO vehicleTypeDTO) {
        if (vehicleTypeDTO == null) {
            return null;
        }
        return VehicleType.builder()
                .id(vehicleTypeDTO.getId())
                .name(vehicleTypeDTO.getName())
                .build();
    }
}

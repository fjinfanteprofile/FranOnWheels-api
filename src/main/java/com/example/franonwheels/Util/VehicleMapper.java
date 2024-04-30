package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.VehicleDTO;
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
                .type(VehicleTypeMapper.vehicleTypeToVehicleTypeDTO(vehicle.getType()))
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .licensePlate(vehicle.getLicensePlate())
                .gearbox(vehicle.getGearbox())
                .displacementCc(vehicle.getDisplacementCc())
                .active(vehicle.getActive())
                .build();
    }

    public Vehicle vehicleConvertToEntity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            throw new IllegalArgumentException("VehicleDTO provided is null");
        }

        return Vehicle.builder()
                .id(vehicleDTO.getId())
                .type(VehicleTypeMapper.vehicleTypeDTOToEntity(vehicleDTO.getType()))
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .licensePlate(vehicleDTO.getLicensePlate())
                .gearbox(vehicleDTO.getGearbox())
                .displacementCc(vehicleDTO.getDisplacementCc())
                .active(vehicleDTO.getActive())
                .build();
    }


}

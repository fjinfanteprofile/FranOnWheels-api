package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;

public class VehicleTypeMapper {

    public static VehicleTypeDTO vehicleTypeToVehicleTypeDTO(VehicleType vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        return VehicleTypeDTO.builder()
               .name(vehicleType.getName())
               .build();
    }

    public static VehicleType vehicleTypeDTOToEntity(VehicleTypeDTO vehicleTypeDTO) {
        if (vehicleTypeDTO == null) {
            return null;
        }
        return VehicleType.builder()
               .name(vehicleTypeDTO.getName())
               .build();
    }
}

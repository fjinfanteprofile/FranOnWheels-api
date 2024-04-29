package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VehicleTypeMapper {

    public VehicleTypeDTO vehicleTypeToVehicleTypeDTO(VehicleType vehicleType) {
        if (vehicleType == null) {
            return null;
        }
        return VehicleTypeDTO.builder()
                .id(vehicleType.getId())
               .name(vehicleType.getName())
                .active(vehicleType.getActive())
               .build();
    }

    public VehicleType vehicleTypeDTOToEntity(VehicleTypeDTO vehicleTypeDTO) {
        if (vehicleTypeDTO == null) {
            return null;
        }
        return VehicleType.builder()
                .id(vehicleTypeDTO.getId()) // Set the ID
                .name(vehicleTypeDTO.getName())
                .active(vehicleTypeDTO.getActive())
                .build();
    }
}

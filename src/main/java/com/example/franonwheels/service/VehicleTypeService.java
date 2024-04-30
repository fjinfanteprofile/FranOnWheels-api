package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeService {

    VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleTypeDTO);

    List<VehicleTypeDTO> getAllVehicleTypes();

    Optional<VehicleTypeDTO> getVehicleTypeById(Long id);

    Optional<VehicleTypeDTO> updateVehicleType(VehicleTypeDTO vehicleTypeDTO, Long id);

    void deleteVehicleTypeById(Long id);
}

package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeService {

    VehicleType createVehicleType(VehicleType vehicleType);

    List<VehicleType> getAllVehicleTypes();

    Optional<VehicleType> getVehicleTypeById(Long id);

    VehicleType updateVehicleType(VehicleType vehicleType);

    void deleteVehicleTypeById(Long id);
}

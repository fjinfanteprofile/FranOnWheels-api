package com.example.demo.service;

import com.example.demo.model.domain.VehicleType;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeService {

    VehicleType createVehicleType(VehicleType vehicleType);

    List<VehicleType> getAllVehicleTypes();

    Optional<VehicleType> getVehicleTypeById(Long id);

    VehicleType updateVehicleType(VehicleType vehicleType);

    void deleteVehicleTypeById(Long id);
}

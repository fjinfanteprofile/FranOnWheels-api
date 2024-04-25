package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.VehicleDTO;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    Optional<VehicleDTO> getVehicleById(Long id);

    Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO, Long id);

    void deleteVehicleById(Long id);
}

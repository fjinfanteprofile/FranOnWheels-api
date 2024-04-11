package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Optional<Vehicle> getVehicleById(Long id);

    Vehicle updateVehicle(Vehicle vehicle);

    void deleteVehicleById(Long id);
}

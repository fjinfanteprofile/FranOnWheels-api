package com.example.demo.service;

import com.example.demo.model.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle createVehicle(Vehicle vehicle);

    List<Vehicle> getAllVehicles();

    Optional<Vehicle> getVehicleById(Long id);

    Vehicle updateVehicle(Vehicle vehicle);

    void deleteVehicleById(Long id);
}

package com.example.franonwheels.service;

import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.VehicleDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    List<VehicleDTO> getAllVehicles();

    Optional<VehicleDTO> getVehicleById(Long id);

    Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO, Long id);

    void deactivateVehicleById(Long id);

    void activateVehicleById(Long id);

    public List<VehicleDTO> getActiveVehicles();

    public List<VehicleDTO> getInactiveVehicles();

    List<VehicleDTO> getActiveVehiclesByTypeName(String typeName);
}

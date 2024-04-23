package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.dtos.VehicleDTO;
import com.example.franonwheels.repository.VehicleRepository;
import com.example.franonwheels.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    // Create operation
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        return VehicleMapper.vehicleConvertToDTO(this.vehicleRepository.save(VehicleMapper.vehicleConvertToEntity(vehicleDTO)));
    }

    // Read operations
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> vehicles = vehicleRepository.findAll();
        return vehicles.stream()
                .map(VehicleMapper::vehicleConvertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleDTO> getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        return vehicle.map(VehicleMapper::vehicleConvertToDTO);
    }

    // Update operation
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        return VehicleMapper.vehicleConvertToDTO(this.vehicleRepository.save(VehicleMapper.vehicleConvertToEntity(vehicleDTO)));

    }

    // Delete operation
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }
}

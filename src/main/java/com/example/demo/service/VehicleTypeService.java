package com.example.demo.service;

import com.example.demo.model.domain.VehicleType;
import com.example.demo.repository.VehicleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    // Create operation
    public VehicleType createVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    // Read operations
    public List<VehicleType> getAllVehicleTypes() {
        return vehicleTypeRepository.findAll();
    }

    public Optional<VehicleType> getVehicleTypeById(Long id) {
        return vehicleTypeRepository.findById(id);
    }

    // Update operation
    public VehicleType updateVehicleType(VehicleType vehicleType) {
        return vehicleTypeRepository.save(vehicleType);
    }

    // Delete operation
    public void deleteVehicleTypeById(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}

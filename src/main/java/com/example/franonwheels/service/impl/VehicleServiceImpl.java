package com.example.franonwheels.service.impl;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl {

    private final VehicleRepository vehicleRepository;


    // Create operation
    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Read operations
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    // Update operation
    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    // Delete operation
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }
}

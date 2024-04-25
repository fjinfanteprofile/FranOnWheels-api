package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.Util.VehicleTypeMapper;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.model.dtos.VehicleDTO;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import com.example.franonwheels.repository.VehicleRepository;
import com.example.franonwheels.repository.VehicleTypeRepository;
import com.example.franonwheels.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleTypeRepository vehicleTypeRepository;

    // Create operation
    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        // Convert VehicleDTO to Vehicle entity
        Vehicle vehicle = VehicleMapper.vehicleConvertToEntity(vehicleDTO);

        // If VehicleTypeDTO is present
        if (vehicleDTO.getType() != null && vehicleDTO.getType().getId() != null) {
            Long vehicleTypeId = vehicleDTO.getType().getId();
            // Check if VehicleType with the provided ID exists in the database
            Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findById(vehicleTypeId);
            if (optionalVehicleType.isPresent()) {
                // If VehicleType exists, set it directly in the Vehicle
                vehicle.setType(optionalVehicleType.get());
            } else {
                // If VehicleType does not exist, throw an exception or handle the scenario accordingly
                throw new NoSuchElementException("VehicleType not found with ID: " + vehicleTypeId);
            }
        } else {
            // Handle scenario when VehicleType ID is not provided
            // This part remains the same as your original implementation
            VehicleTypeDTO vehicleTypeDTO = vehicleDTO.getType();
            VehicleType vehicleType;
            if (vehicleTypeDTO != null && vehicleTypeDTO.getName() != null) {
                // Find existing VehicleType by name
                vehicleType = vehicleTypeRepository.findByName(vehicleTypeDTO.getName());
            } else {
                vehicleType = null;
            }
            if (vehicleType == null) {
                // Create a new VehicleType entity and save it
                vehicleType = VehicleTypeMapper.vehicleTypeDTOToEntity(vehicleTypeDTO);
                vehicleType = vehicleTypeRepository.save(vehicleType);
            }
            vehicle.setType(vehicleType);

            // Set the ID and name of the VehicleTypeDTO
            vehicleTypeDTO.setId(vehicleType.getId());
            vehicleTypeDTO.setName(vehicleType.getName());
        }

        // Save the Vehicle entity
        vehicle = vehicleRepository.save(vehicle);

        // Convert saved Vehicle entity back to VehicleDTO and return
        return VehicleMapper.vehicleConvertToDTO(vehicle);
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
    public Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO, Long id) {
        VehicleDTO updatedVehicle = VehicleMapper.vehicleConvertToDTO(this.vehicleRepository.save(VehicleMapper.vehicleConvertToEntity(vehicleDTO)));
        return Optional.of(updatedVehicle);

    }

    // Delete operation
    public void deleteVehicleById(Long id) {
        vehicleRepository.deleteById(id);
    }

}

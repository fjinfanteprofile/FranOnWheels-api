package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.Util.VehicleTypeMapper;
import com.example.franonwheels.model.domain.Vehicle;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleDTO;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import com.example.franonwheels.repository.VehicleRepository;
import com.example.franonwheels.repository.VehicleTypeRepository;
import com.example.franonwheels.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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
        if (Objects.nonNull(vehicleDTO.getType()) && Objects.nonNull(vehicleDTO.getType().getId())) {
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

    public Optional<VehicleDTO> updateVehicle(VehicleDTO vehicleDTO, Long id) {
        // Retrieve the existing vehicle entity from the repository
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        if (optionalVehicle.isPresent()) {
            Vehicle existingVehicle = optionalVehicle.get();

            // Update the fields of the existing entity with the new data from the DTO
            existingVehicle.setModel(vehicleDTO.getModel());
            existingVehicle.setYear(vehicleDTO.getYear());
            existingVehicle.setLicensePlate(vehicleDTO.getLicensePlate());
            existingVehicle.setGearbox(vehicleDTO.getGearbox());
            existingVehicle.setDisplacementCc(vehicleDTO.getDisplacementCc());
            if (vehicleDTO.getType() != null) {
                existingVehicle.setType(vehicleTypeRepository.findById(vehicleDTO.getType().getId())
                        .orElseThrow(() -> new NoSuchElementException("Type not found")));
            }

            // Save the updated entity
            existingVehicle = vehicleRepository.save(existingVehicle);

            // Convert the updated entity to DTO and return it
            return Optional.of(VehicleMapper.vehicleConvertToDTO(existingVehicle));
        } else {
            // If the vehicle with the specified ID does not exist, return an empty optional
            return Optional.empty();
        }
    }

    // soft Delete operation
    public void deactivateVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        optionalVehicle.ifPresent(vehicle -> {
            vehicle.setActive(0); // Set active to 0 (inactive)
            vehicleRepository.save(vehicle);
        });
    }

    // Activate operation
    public void activateVehicleById(Long id) {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        optionalVehicle.ifPresent(vehicle -> {
            vehicle.setActive(1); // Set active to 1 (active)
            vehicleRepository.save(vehicle);
        });
    }

    public List<VehicleDTO> getActiveVehicles() {
        List<Vehicle> activeVehicles = vehicleRepository.findByActive(1); // Assuming active vehicles have active = 1
        return activeVehicles.stream()
                .map(VehicleMapper::vehicleConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<VehicleDTO> getInactiveVehicles() {
        List<Vehicle> inactiveVehicles = vehicleRepository.findByActive(0); // Assuming inactive vehicles have active = 0
        return inactiveVehicles.stream()
                .map(VehicleMapper::vehicleConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<VehicleDTO> getActiveVehiclesByTypeName(String typeName) {
        List<Vehicle> activeVehicles = vehicleRepository.findByType_NameIgnoreCaseAndActive(typeName, 1);
        return activeVehicles.stream()
                .map(VehicleMapper::vehicleConvertToDTO)
                .collect(Collectors.toList());
    }

}

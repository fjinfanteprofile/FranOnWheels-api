package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.Util.VehicleTypeMapper;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import com.example.franonwheels.repository.VehicleTypeRepository;
import com.example.franonwheels.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    // Create operation
    public VehicleTypeDTO createVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        return VehicleTypeMapper.vehicleTypeToVehicleTypeDTO(this.vehicleTypeRepository.save(VehicleTypeMapper.vehicleTypeDTOToEntity(vehicleTypeDTO)));

    }

    // Read operations
    public List<VehicleTypeDTO> getAllVehicleTypes() {
        List<VehicleType> vehicleTypes = vehicleTypeRepository.findAll();
        return vehicleTypes.stream()
                .map(VehicleTypeMapper::vehicleTypeToVehicleTypeDTO)
                .collect(Collectors.toList());
    }

    public Optional<VehicleTypeDTO> getVehicleTypeById(Long id) {
        Optional<VehicleType> vehicleType = vehicleTypeRepository.findById(id);
        return vehicleType.map(VehicleTypeMapper::vehicleTypeToVehicleTypeDTO);
    }

    // Update operation
    public Optional<VehicleTypeDTO> updateVehicleType(VehicleTypeDTO vehicleTypeDTO, Long id) {

        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findById(id);
        if (optionalVehicleType.isPresent()) {
            VehicleType existingVehicleType = optionalVehicleType.get();
            existingVehicleType.setName(vehicleTypeDTO.getName());
            existingVehicleType = vehicleTypeRepository.save(existingVehicleType);

            return Optional.of(VehicleTypeMapper.vehicleTypeToVehicleTypeDTO(existingVehicleType));
        } else {
            // If the vehicle type with the specified ID does not exist, return an empty optional
            return Optional.empty();
        }
    }

    // Delete operation
    public void deleteVehicleTypeById(Long id) {
        vehicleTypeRepository.deleteById(id);
    }

    // Activate operation
    public void activateVehicleTypeById(Long id) {
        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findById(id);
        optionalVehicleType.ifPresent(vehicleType -> {
            vehicleType.setActive(1);
            vehicleTypeRepository.save(vehicleType);
        });
    }

    // Deactivate operation
    public void deactivateVehicleTypeById(Long id) {
        Optional<VehicleType> optionalVehicleType = vehicleTypeRepository.findById(id);
        optionalVehicleType.ifPresent(vehicleType -> {
            vehicleType.setActive(0);
            vehicleTypeRepository.save(vehicleType);
        });
    }

    // Show active vehicle types
    public List<VehicleTypeDTO> getActiveVehicleTypes() {
        List<VehicleType> activeVehicleTypes = vehicleTypeRepository.findByActive(1);
        return activeVehicleTypes.stream()
                .map(VehicleTypeMapper::vehicleTypeToVehicleTypeDTO)
                .collect(Collectors.toList());
    }

    // Show inactive vehicle types
    public List<VehicleTypeDTO> getInactiveVehicleTypes() {
        List<VehicleType> inactiveVehicleTypes = vehicleTypeRepository.findByActive(0);
        return inactiveVehicleTypes.stream()
                .map(VehicleTypeMapper::vehicleTypeToVehicleTypeDTO)
                .collect(Collectors.toList());
    }
}

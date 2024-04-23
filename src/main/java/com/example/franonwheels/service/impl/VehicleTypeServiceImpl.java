package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.VehicleMapper;
import com.example.franonwheels.Util.VehicleTypeMapper;
import com.example.franonwheels.model.domain.VehicleType;
import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import com.example.franonwheels.repository.VehicleTypeRepository;
import com.example.franonwheels.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public VehicleTypeDTO updateVehicleType(VehicleTypeDTO vehicleTypeDTO) {
        return VehicleTypeMapper.vehicleTypeToVehicleTypeDTO(this.vehicleTypeRepository.save(VehicleTypeMapper.vehicleTypeDTOToEntity(vehicleTypeDTO)));

    }

    // Delete operation
    public void deleteVehicleTypeById(Long id) {
        vehicleTypeRepository.deleteById(id);
    }
}

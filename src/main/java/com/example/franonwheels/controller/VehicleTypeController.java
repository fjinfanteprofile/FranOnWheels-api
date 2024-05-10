package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.VehicleTypeDTO;
import com.example.franonwheels.service.impl.VehicleTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/vehicle-types")
@RequiredArgsConstructor
public class VehicleTypeController {

    private final VehicleTypeServiceImpl vehicleTypeServiceImpl;

    @PostMapping
    public ResponseEntity<VehicleTypeDTO> createVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO) {
        VehicleTypeDTO createdVehicleType = vehicleTypeServiceImpl.createVehicleType(vehicleTypeDTO);
        return new ResponseEntity<>(createdVehicleType, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleTypeDTO>> getAllVehicleTypes() {
        List<VehicleTypeDTO> allVehicleTypes = vehicleTypeServiceImpl.getAllVehicleTypes();
        return new ResponseEntity<>(allVehicleTypes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleTypeDTO> getVehicleTypeById(@PathVariable Long id) {
        Optional<VehicleTypeDTO> vehicleType = vehicleTypeServiceImpl.getVehicleTypeById(id);
        return vehicleType.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleTypeDTO> updateVehicleType(@RequestBody VehicleTypeDTO vehicleTypeDTO, @PathVariable Long id) {
        // Pass both vehicleTypeDTO and id to the service method
        Optional<VehicleTypeDTO> updatedVehicleTypeOptional = vehicleTypeServiceImpl.updateVehicleType(vehicleTypeDTO, id);

        // Check if the vehicle type was updated successfully
        if (updatedVehicleTypeOptional.isPresent()) {
            return new ResponseEntity<>(updatedVehicleTypeOptional.get(), HttpStatus.OK);
        } else {
            // Handle the case where the vehicle type with the specified ID does not exist
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleTypeById(@PathVariable Long id) {
        vehicleTypeServiceImpl.deactivateVehicleTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateVehicleTypeById(@PathVariable Long id) {
        vehicleTypeServiceImpl.activateVehicleTypeById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<VehicleTypeDTO>> getActiveVehicleTypes() {
        List<VehicleTypeDTO> activeVehicleTypes = vehicleTypeServiceImpl.getActiveVehicleTypes();
        return new ResponseEntity<>(activeVehicleTypes, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<VehicleTypeDTO>> getInactiveVehicleTypes() {
        List<VehicleTypeDTO> inactiveVehicleTypes = vehicleTypeServiceImpl.getInactiveVehicleTypes();
        return new ResponseEntity<>(inactiveVehicleTypes, HttpStatus.OK);
    }
}

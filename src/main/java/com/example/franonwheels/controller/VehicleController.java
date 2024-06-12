package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.VehicleDTO;
import com.example.franonwheels.service.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleServiceImpl vehicleServiceImpl;

    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createdVehicle = vehicleServiceImpl.createVehicle(vehicleDTO);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDTO> vehicle = vehicleServiceImpl.getVehicleById(id);
        return vehicle.map(vehicleDTO -> new ResponseEntity<>(vehicleDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<VehicleDTO>> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @PathVariable Long id) {
        Optional<VehicleDTO> updatedVehicle = vehicleServiceImpl.updateVehicle(vehicleDTO, id);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id) {
        vehicleServiceImpl.deactivateVehicleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateVehicleById(@PathVariable Long id) {
        vehicleServiceImpl.activateVehicleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<VehicleDTO>> getActiveVehicles() {
        List<VehicleDTO> activeVehicles = vehicleServiceImpl.getActiveVehicles();
        return new ResponseEntity<>(activeVehicles, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<VehicleDTO>> getInactiveVehicles() {
        List<VehicleDTO> inactiveVehicles = vehicleServiceImpl.getInactiveVehicles();
        return new ResponseEntity<>(inactiveVehicles, HttpStatus.OK);
    }
    @GetMapping("/active/{typeName}")
    public ResponseEntity<List<VehicleDTO>> getActiveVehiclesByTypeName(@PathVariable String typeName) {
        List<VehicleDTO> activeVehicles = vehicleServiceImpl.getActiveVehiclesByTypeName(typeName);
        return new ResponseEntity<>(activeVehicles, HttpStatus.OK);
    }
}

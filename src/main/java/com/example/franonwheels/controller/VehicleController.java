package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.UserDTO;
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

    @PostMapping("/create")
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {
        VehicleDTO createdVehicle = vehicleServiceImpl.createVehicle(vehicleDTO);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/showall")
    public ResponseEntity<List<VehicleDTO>> getAllVehicles() {
        List<VehicleDTO> vehicles = vehicleServiceImpl.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/showbyid")
    public ResponseEntity<VehicleDTO> getVehicleById(@RequestParam Long id) {
        Optional<VehicleDTO> vehicle = vehicleServiceImpl.getVehicleById(id);
        return vehicle.map(vehicleDTO -> new ResponseEntity<>(vehicleDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<VehicleDTO>> updateVehicle(@RequestBody VehicleDTO vehicleDTO, @RequestParam Long id) {
        Optional<VehicleDTO> updatedVehicle = vehicleServiceImpl.updateVehicle(vehicleDTO, id);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteVehicleById(@RequestParam Long id) {
        vehicleServiceImpl.deactivateVehicleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/activate")
    public ResponseEntity<Void> activateVehicleById(@RequestParam Long id) {
        vehicleServiceImpl.activateVehicleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
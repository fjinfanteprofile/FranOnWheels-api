package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.model.dtos.SpecialityDTO;
import com.example.franonwheels.service.impl.SpecialityServiceImpl;
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
@RequestMapping("/specialities")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialityServiceImpl specialityServiceImpl;

    // Create a speciality
    @PostMapping
    public ResponseEntity<SpecialityDTO> createSpeciality(@RequestBody SpecialityDTO specialityDTO) {
        SpecialityDTO createdSpeciality = specialityServiceImpl.createSpeciality(specialityDTO);
        return new ResponseEntity<>(createdSpeciality, HttpStatus.CREATED);
    }

    // Get all specialities
    @GetMapping
    public ResponseEntity<List<SpecialityDTO>> getAllSpecialities() {
        List<SpecialityDTO> allSpecialities = specialityServiceImpl.getAllSpecialities();
        return new ResponseEntity<>(allSpecialities, HttpStatus.OK);
    }

    // Get a speciality by ID
    @GetMapping("/{id}")
    public ResponseEntity<SpecialityDTO> getSpecialityById(@PathVariable Long id) {
        Optional<SpecialityDTO> specialityDto = specialityServiceImpl.getSpecialityById(id);
        return specialityDto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a speciality
    @PutMapping("/{id}")
    public ResponseEntity<Optional<SpecialityDTO>> updateSpeciality(@RequestBody SpecialityDTO specialityDTO, @PathVariable Long id) {
        Optional<SpecialityDTO> updatedSpeciality = specialityServiceImpl.updateSpeciality(specialityDTO,id);
        return new ResponseEntity<>(updatedSpeciality, HttpStatus.OK);
    }

    // Delete a speciality
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialityById(@PathVariable Long id) {
        specialityServiceImpl.deactivateSpecialityById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Activate a speciality
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateSpecialityById(@PathVariable Long id) {
        specialityServiceImpl.activateSpecialityById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get active specialities
    @GetMapping("/active")
    public ResponseEntity<List<SpecialityDTO>> getActiveSpecialities() {
        List<SpecialityDTO> activeSpecialities = specialityServiceImpl.getActiveSpecialities();
        return new ResponseEntity<>(activeSpecialities, HttpStatus.OK);
    }

    // Get inactive specialities
    @GetMapping("/inactive")
    public ResponseEntity<List<SpecialityDTO>> getInactiveSpecialities() {
        List<SpecialityDTO> inactiveSpecialities = specialityServiceImpl.getInactiveSpecialities();
        return new ResponseEntity<>(inactiveSpecialities, HttpStatus.OK);
    }
}


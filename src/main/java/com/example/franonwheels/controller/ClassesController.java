package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.BookingsDTO;
import com.example.franonwheels.model.dtos.ClassesDTO;
import com.example.franonwheels.service.impl.ClassesServiceImpl;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassesController {

    private final ClassesServiceImpl classesServiceImpl;

    // Create a class
    @PostMapping("/{id}")
    public ResponseEntity<ClassesDTO> createClass(@RequestBody ClassesDTO classesDTO, @PathVariable Long id) {

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        classesDTO.setUserId(id);

        ClassesDTO createdClass = classesServiceImpl.createClass(classesDTO, id); // Pass userId to the service method

        if (createdClass != null) {
            return new ResponseEntity<>(createdClass, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all classes
    @GetMapping
    public ResponseEntity<List<ClassesDTO>> getAllClasses() {
        List<ClassesDTO> allClasses = classesServiceImpl.getAllClasses();
        return new ResponseEntity<>(allClasses, HttpStatus.OK);
    }

    // Get a class by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassesDTO> getClassById(@PathVariable Long id) {
        Optional<ClassesDTO> classDto = classesServiceImpl.getClassById(id);
        return classDto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a class
    @PutMapping("/{id}")
    public ResponseEntity<ClassesDTO> updateClass(@RequestBody ClassesDTO classesDTO, @PathVariable Long id) {
        ClassesDTO updatedClass = classesServiceImpl.updateClass(classesDTO, id);
        return new ResponseEntity<>(updatedClass, HttpStatus.OK);
    }

    // Delete a class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassById(@PathVariable Long id) {
        classesServiceImpl.deactivateClassById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Activate a class
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateClassById(@PathVariable Long id) {
        classesServiceImpl.activateClassById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get active classes
    @GetMapping("/active")
    public ResponseEntity<List<ClassesDTO>> getActiveClasses() {
        List<ClassesDTO> activeClasses = classesServiceImpl.getActiveClasses();
        return new ResponseEntity<>(activeClasses, HttpStatus.OK);
    }

    // Get inactive classes
    @GetMapping("/inactive")
    public ResponseEntity<List<ClassesDTO>> getInactiveClasses() {
        List<ClassesDTO> inactiveClasses = classesServiceImpl.getInactiveClasses();
        return new ResponseEntity<>(inactiveClasses, HttpStatus.OK);
    }

    @GetMapping("/available-time-slots/{date}")
    public ResponseEntity<List<String>> getAvailableTimeSlots(@PathVariable LocalDate date) {
        List<String> availableTimeSlots = classesServiceImpl.getAvailableTimeSlotsForDate(date);
        return new ResponseEntity<>(availableTimeSlots, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ClassesDTO>> getClassesByUserId(@PathVariable Long userId) {
        List<ClassesDTO> classes = classesServiceImpl.getClassesByUserId(userId);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

}


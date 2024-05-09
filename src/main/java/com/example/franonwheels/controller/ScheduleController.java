package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.ScheduleDTO;
import com.example.franonwheels.service.impl.ScheduleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleServiceImpl scheduleServiceImpl;

    @PostMapping
    public ResponseEntity<ScheduleDTO> createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        ScheduleDTO createdSchedule = scheduleServiceImpl.createSchedule(scheduleDTO);
        return new ResponseEntity<>(createdSchedule, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<ScheduleDTO> allSchedules = scheduleServiceImpl.getAllSchedules();
        return new ResponseEntity<>(allSchedules, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> getScheduleById(@PathVariable Long id) {
        Optional<ScheduleDTO> schedule = scheduleServiceImpl.getScheduleById(id);
        return schedule.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ScheduleDTO>> updateSchedule(@RequestBody ScheduleDTO scheduleDTO, @PathVariable Long id) {
        Optional<ScheduleDTO> updatedSchedule = scheduleServiceImpl.updateSchedule(scheduleDTO,id);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    // Activate a schedule
    @PatchMapping("/activate/{id}")
    public ResponseEntity<Void> activateScheduleById(@PathVariable Long id) {
        scheduleServiceImpl.activateScheduleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get active schedules
    @GetMapping("/active")
    public ResponseEntity<List<ScheduleDTO>> getActiveSchedules() {
        List<ScheduleDTO> activeSchedules = scheduleServiceImpl.getActiveSchedules();
        return new ResponseEntity<>(activeSchedules, HttpStatus.OK);
    }

    // Deactivate a schedule
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleById(@PathVariable Long id) {
        scheduleServiceImpl.deactivateScheduleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get inactive schedules
    @GetMapping("/inactive")
    public ResponseEntity<List<ScheduleDTO>> getInactiveSchedules() {
        List<ScheduleDTO> inactiveSchedules = scheduleServiceImpl.getInactiveSchedules();
        return new ResponseEntity<>(inactiveSchedules, HttpStatus.OK);
    }


}

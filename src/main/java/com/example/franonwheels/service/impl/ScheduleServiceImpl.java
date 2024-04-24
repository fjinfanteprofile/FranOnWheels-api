package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.ScheduleMapper;
import com.example.franonwheels.model.domain.Schedule;
import com.example.franonwheels.model.dtos.ScheduleDTO;
import com.example.franonwheels.repository.ScheduleRepository;
import com.example.franonwheels.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    // Create operation
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        return ScheduleMapper.toDTO(this.scheduleRepository.save(ScheduleMapper.toEntity(scheduleDTO)));
    }

    // Read operations
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(ScheduleMapper::toDTO) // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    public Optional<ScheduleDTO> getScheduleById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.map(ScheduleMapper::toDTO); // Convert entity to DTO if present
    }

    // Update operation
    public ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO) {
        return ScheduleMapper.toDTO(this.scheduleRepository.save(ScheduleMapper.toEntity(scheduleDTO)));
    }

    // Delete operation
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}

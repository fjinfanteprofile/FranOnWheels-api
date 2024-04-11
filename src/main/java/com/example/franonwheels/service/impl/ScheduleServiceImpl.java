package com.example.franonwheels.service.impl;

import com.example.franonwheels.model.domain.Schedule;
import com.example.franonwheels.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl {

    private final ScheduleRepository scheduleRepository;


    // Create operation
    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Read operations
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    // Update operation
    public Schedule updateSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    // Delete operation
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
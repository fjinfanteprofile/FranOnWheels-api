package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Schedule;
import com.example.franonwheels.model.dtos.ScheduleDTO;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getAllSchedules();

    Optional<ScheduleDTO> getScheduleById(Long id);

    ScheduleDTO updateSchedule(ScheduleDTO scheduleDTO);

    void deleteScheduleById(Long id);
}

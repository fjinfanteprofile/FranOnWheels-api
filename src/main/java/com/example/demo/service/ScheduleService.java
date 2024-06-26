package com.example.demo.service;

import com.example.demo.model.domain.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule createSchedule(Schedule schedule);

    List<Schedule> getAllSchedules();

    Optional<Schedule> getScheduleById(Long id);

    Schedule updateSchedule(Schedule schedule);

    void deleteScheduleById(Long id);
}

package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Schedule;
import com.example.franonwheels.model.dtos.ScheduleDTO;

public class ScheduleMapper {

    public static ScheduleDTO toDTO(Schedule schedule) {

        if (schedule == null) {

            return null;

        }

        return ScheduleDTO.builder()
                .dayOfWeek(schedule.getDayOfWeek())
                .starttime(schedule.getStarttime())
                .endtime(schedule.getEndtime())
                .active(schedule.getActive())
                .build();

    }

    public static Schedule toEntity(ScheduleDTO scheduleDTO) {

        if (scheduleDTO == null) {

            return null;

        }

        return Schedule.builder()
                .dayOfWeek(scheduleDTO.getDayOfWeek())
                .starttime(scheduleDTO.getStarttime())
                .endtime(scheduleDTO.getEndtime())
                .active(scheduleDTO.getActive())
                .build();

    }

}

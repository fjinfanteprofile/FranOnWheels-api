package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleDTO {
    private String dayOfWeek;
    private String starttime;
    private String endtime;
    private Integer active;
}

package com.example.demo.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScheduleDTO {
    private Long id;
    private String dayOfWeek;
    private String starttime;
    private String endtime;
}

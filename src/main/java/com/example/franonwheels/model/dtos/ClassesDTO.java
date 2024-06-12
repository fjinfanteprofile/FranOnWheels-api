package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
public class ClassesDTO {
    private Long id;
    private Long vehicleId;
    private LocalDate date;
    private String timeStart;
    private String timeEnd;
    private Integer active;
    private Long userId;
    private String dayOfWeek;
}

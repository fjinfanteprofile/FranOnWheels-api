package com.example.demo.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Builder
public class ClassesDTO {
    private Long id;
    private Long vehicleId;
    private Date date;
    private String timeStart;
    private String timeEnd;
}

package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookingsDTO {

    private Long id;
    private Long classId;
    private Long userId;
    private Integer active;
}

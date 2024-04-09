package com.example.demo.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SpecialityDTO {
    private Long id;
    private String name;
}

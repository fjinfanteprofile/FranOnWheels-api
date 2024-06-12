package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleDTO {
    private Long id;
    private String name;
    private Integer active;
}

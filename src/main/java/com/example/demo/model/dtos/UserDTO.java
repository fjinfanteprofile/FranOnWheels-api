package com.example.demo.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String address;
    private String email;
    private com.example.demo.model.dto.RoleDTO role;
    private com.example.demo.model.dto.SpecialityDTO speciality;
}

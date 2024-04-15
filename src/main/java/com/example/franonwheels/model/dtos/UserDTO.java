package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String address;
    private String email;
    private com.example.franonwheels.model.dtos.RoleDTO role;
    private com.example.franonwheels.model.dtos.SpecialityDTO speciality;
}

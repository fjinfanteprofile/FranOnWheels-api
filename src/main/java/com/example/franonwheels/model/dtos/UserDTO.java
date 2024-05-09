package com.example.franonwheels.model.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String name;
    private String lastName;
    private String dni;
    private String phoneNumber;
    private String address;
    private String email;
    private String password;
    private Integer age;
    private RoleDTO role;
    private Integer active;
}

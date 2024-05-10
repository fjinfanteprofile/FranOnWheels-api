package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.model.dtos.UserDTO;
import lombok.experimental.UtilityClass;


import java.util.NoSuchElementException;

@UtilityClass
public class UserMapper {


    public UserDTO userConvertToDTO(User user){
        if (user == null){
            throw new NoSuchElementException("User provided is null");
        }
        RoleDTO roleDTO = null;
        if (user.getRole() != null) {
            roleDTO = RoleDTO.builder()
                    .id(user.getRole().getId())
                    .active(user.getRole().getActive())
                    .name(user.getRole().getName())
                    .build();
        }
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .role(roleDTO)
                .active(user.getActive())
                .build();
    }

    public User userConvertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO provided is null");
        }

        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .age(userDTO.getAge())
                .dni(userDTO.getDni())
                .role(RoleMapper.dtoToRole(userDTO.getRole()))
                .password(userDTO.getPassword())
                .active(userDTO.getActive())
                .build();
    }
}

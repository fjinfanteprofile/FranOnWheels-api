package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.model.dtos.SpecialityDTO;
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
                    .name(user.getRole().getName())
                    .build();
        }

        SpecialityDTO specialityDTO = null;
        if (user.getSpeciality() != null) {
            specialityDTO = SpecialityDTO.builder()
                    .id(user.getSpeciality().getId())
                    .name(user.getSpeciality().getName())
                    .build();
        }
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .role(roleDTO)
                .speciality(specialityDTO)
                .build();
    }

    public User userConvertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO provided is null");
        }

        return User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .age(userDTO.getAge())
                .dni(userDTO.getDni())
                .role(RoleMapper.dtoToRole(userDTO.getRole()))
                .speciality(SpecialityMapper.SpecialityDTOtoEntity(userDTO.getSpeciality()))
                .password(userDTO.getPassword())
                .build();
    }
}

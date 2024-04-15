package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.model.dtos.SpecialityDTO;
import com.example.franonwheels.model.dtos.UserDTO;

import java.util.NoSuchElementException;

public class UserMapper {

    public static UserDTO userConvertToDTO(User user){

        if (user==null){
            throw new NoSuchElementException("User provided is null");
        }
        return UserDTO.builder()

                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .email(user.getEmail())
                .role(roleConvertToDTO(user.getRole()))
                .speciality(specialityConvertToDTO(user.getSpeciality()))
                .build();
    }

    public static User userConvertToEntity(UserDTO usersDTO) {
        if (usersDTO == null) {
            throw new IllegalArgumentException("UserDTO provided is null");
        }

        return User.builder()
                .name(usersDTO.getName())
                .lastName(usersDTO.getLastName())
                .email(usersDTO.getEmail())
                .address(usersDTO.getAddress())
                .phoneNumber(usersDTO.getPhoneNumber())
                .dni(usersDTO.getDni())
                .role(roleConvertToEntity(usersDTO.getRole()))
                .speciality(specialityConvertToEntity(usersDTO.getSpeciality()))
                .build();
    }

    private static RoleDTO roleConvertToDTO(Role role) {
        if (role == null) {
            return null;
        }
        return RoleDTO.builder()
                .name(role.getName())
                .build();
    }
    private static Speciality specialityConvertToEntity(SpecialityDTO specialityDTO) {
        if (specialityDTO == null) {
            return null;
        }
        return Speciality.builder()
                .name(specialityDTO.getName())
                .build();
    }
    private static Role roleConvertToEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        return Role.builder()
                .name(roleDTO.getName())
                .build();
    }
    private static SpecialityDTO specialityConvertToDTO(Speciality speciality) {
        if (speciality == null) {
            return null;
        }
        return SpecialityDTO.builder()
                .name(speciality.getName())
                .build();
    }




}
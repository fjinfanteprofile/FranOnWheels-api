package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.dtos.RoleDTO;

public class RoleMapper {

    public static RoleDTO roletoDTO(Role role) {

        if (role == null) {
            return null;
        }
        return RoleDTO.builder()
                .id(role.getId())
                .active(role.getActive())
                .name(role.getName())
                .build();
    }

    public static Role dtoToRole(RoleDTO roleDTO) {

        if (roleDTO == null) {
            return null;
        }

        return Role.builder()
                .id(roleDTO.getId())
                .active(roleDTO.getActive())
                .name(roleDTO.getName())
                .build();
    }

}

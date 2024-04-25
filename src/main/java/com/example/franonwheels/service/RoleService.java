package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.dtos.RoleDTO;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    List<RoleDTO> getAllRoles();

    Optional<RoleDTO> getRoleById(Long id);

    Optional<RoleDTO> updateRole(RoleDTO roleDTO, Long id);

    void deleteRoleById(Long id);
}

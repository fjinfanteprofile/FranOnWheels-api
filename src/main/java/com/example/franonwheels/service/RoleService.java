package com.example.franonwheels.service;

import com.example.franonwheels.Util.RoleMapper;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.dtos.RoleDTO;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public interface RoleService {

    RoleDTO createRole(RoleDTO roleDTO);

    List<RoleDTO> getAllRoles();

    Optional<RoleDTO> getRoleById(Long id);

    Optional<RoleDTO> updateRole(RoleDTO roleDTO, Long id);

    void deactivateRoleById(Long id);

    void activateRoleById(Long id);

    public List<RoleDTO> getActiveRoles();
    // Method to get all inactive roles
    public List<RoleDTO> getInactiveRoles();
}

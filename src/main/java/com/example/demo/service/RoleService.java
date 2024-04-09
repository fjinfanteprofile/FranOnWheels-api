package com.example.demo.service;

import com.example.demo.model.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Role createRole(Role role);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Role updateRole(Role role);

    void deleteRoleById(Long id);
}

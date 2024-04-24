package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.RoleMapper;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    // Create operation
    public RoleDTO createRole(RoleDTO roleDTO) {
        return RoleMapper.roletoDTO(this.roleRepository.save(RoleMapper.dtoToRole(roleDTO)));
    }

    // Read operations
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::roletoDTO) // Convert each entity to DTO
                .collect(Collectors.toList());
    }

    public Optional<RoleDTO> getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(RoleMapper::roletoDTO); // Convert entity to DTO if present
    }

    // Update operation
    public RoleDTO updateRole(RoleDTO roleDTO) {
        return RoleMapper.roletoDTO(this.roleRepository.save(RoleMapper.dtoToRole(roleDTO)));
    }

    // Delete operation
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }
}

package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.RoleMapper;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.repository.UserRepository;
import com.example.franonwheels.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

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
    public Optional<RoleDTO> updateRole(RoleDTO roleDTO, Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role existingRole = optionalRole.get();

            Role updatedRole = Role.builder()
                    .id(existingRole.getId())
                    .name(roleDTO.getName())
                    .build();

            updatedRole = roleRepository.save(updatedRole);
            return Optional.of(RoleMapper.roletoDTO(updatedRole));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found with ID: " + id);
        }
    }

    public void deactivateRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setActive(0); // Set active to 0 (inactive)
            roleRepository.save(role);
        } else {
            throw new NoSuchElementException("Role not found with ID: " + id);
        }
    }

    public void activateRoleById(Long id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setActive(1); // Set active to 1 (active)
            roleRepository.save(role);
        } else {
            throw new NoSuchElementException("Role not found with ID: " + id);
        }
    }

    // Method to get all active roles
    public List<RoleDTO> getActiveRoles() {
        List<Role> activeRoles = roleRepository.findByActive(1);
        return activeRoles.stream()
                .map(RoleMapper::roletoDTO)
                .collect(Collectors.toList());
    }

    // Method to get all inactive roles
    public List<RoleDTO> getInactiveRoles() {
        List<Role> inactiveRoles = roleRepository.findByActive(0);
        return inactiveRoles.stream()
                .map(RoleMapper::roletoDTO)
                .collect(Collectors.toList());
    }
}

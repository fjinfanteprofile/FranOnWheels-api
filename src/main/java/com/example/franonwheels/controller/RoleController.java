package com.example.franonwheels.controller;


import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private  final RoleServiceImpl roleServiceImpl;

    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleServiceImpl.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleServiceImpl.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        Optional<RoleDTO> role = roleServiceImpl.getRoleById(id);
        if (role.isPresent()) {
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<RoleDTO>> updateUser(@RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        Optional<RoleDTO> updatedUser = roleServiceImpl.updateRole(roleDTO,id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deactivateRoleById(@PathVariable Long id) {
        roleServiceImpl.deactivateRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("activate/{id}")
    public ResponseEntity<Void> activateRoleById(@PathVariable Long id) {
        roleServiceImpl.activateRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active")
    public ResponseEntity<List<RoleDTO>> getActiveRoles() {
        List<RoleDTO> activeRoles = roleServiceImpl.getActiveRoles();
        return new ResponseEntity<>(activeRoles, HttpStatus.OK);
    }

    @GetMapping("/inactive")
    public ResponseEntity<List<RoleDTO>> getInactiveRoles() {
        List<RoleDTO> inactiveRoles = roleServiceImpl.getInactiveRoles();
        return new ResponseEntity<>(inactiveRoles, HttpStatus.OK);
    }

}

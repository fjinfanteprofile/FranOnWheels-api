package com.example.franonwheels.controller;


import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PostMapping ("/create")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO) {
        RoleDTO createdRole = roleServiceImpl.createRole(roleDTO);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @GetMapping("/showall")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        List<RoleDTO> roles = roleServiceImpl.getAllRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/showbyid")
    public ResponseEntity<RoleDTO> getRoleById(@RequestParam Long id) {
        Optional<RoleDTO> role = roleServiceImpl.getRoleById(id);
        if (role.isPresent()) {
            return new ResponseEntity<>(role.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<RoleDTO>> updateUser(@RequestBody RoleDTO roleDTO, @RequestParam Long id) {
        Optional<RoleDTO> updatedUser = roleServiceImpl.updateRole(roleDTO,id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deactivateRoleById(@RequestParam Long id) {
        roleServiceImpl.deactivateRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateRoleById(@RequestParam Long id) {
        roleServiceImpl.activateRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/showactive")
    public ResponseEntity<List<RoleDTO>> getActiveRoles() {
        List<RoleDTO> activeRoles = roleServiceImpl.getActiveRoles();
        return new ResponseEntity<>(activeRoles, HttpStatus.OK);
    }

    @GetMapping("/showinactive")
    public ResponseEntity<List<RoleDTO>> getInactiveRoles() {
        List<RoleDTO> inactiveRoles = roleServiceImpl.getInactiveRoles();
        return new ResponseEntity<>(inactiveRoles, HttpStatus.OK);
    }

}

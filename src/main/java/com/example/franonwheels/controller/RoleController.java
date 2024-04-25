package com.example.franonwheels.controller;


import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.service.impl.RoleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/update")
    public ResponseEntity<Optional<RoleDTO>> updateUser(@RequestBody RoleDTO roleDTO, @RequestParam Long id) {
        Optional<RoleDTO> updatedUser = roleServiceImpl.updateRole(roleDTO,id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteUserById(@RequestParam Long id) {
        roleServiceImpl.deleteRoleById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

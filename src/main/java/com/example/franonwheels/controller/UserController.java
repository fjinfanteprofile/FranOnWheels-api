package com.example.franonwheels.controller;

import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.service.impl.UserServiceImpl;
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
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userServiceImpl.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/showall")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userServiceImpl.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/showbyid")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long id) {
        Optional<UserDTO> user = userServiceImpl.getUserById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/update")
    public ResponseEntity<Optional<UserDTO>> updateUser(@RequestBody UserDTO userDTO, @RequestParam Long id) {
        Optional<UserDTO> updatedUser = userServiceImpl.updateUser(userDTO, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<Void> deleteUserById(@RequestParam Long id) {
        userServiceImpl.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/showadmins")
    public ResponseEntity<List<UserDTO>> getAdminUsers() {
        List<UserDTO> adminUsers = userServiceImpl.getAdminUsers();
        return new ResponseEntity<>(adminUsers, HttpStatus.OK);
    }

    @GetMapping("/showbyname")
    public ResponseEntity<List<UserDTO>> getUserByUsername(@RequestParam String name) {
        List<UserDTO> user = userServiceImpl.getUserByUsername(name);
        if (!user.isEmpty()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}

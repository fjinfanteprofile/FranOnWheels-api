package com.example.franonwheels.service;

import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO user);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    UserDTO updateUser(UserDTO userDTO);

    void deleteUserById(Long id);

    List<UserDTO> getAdminUsers();

    public List<UserDTO> getUserByUsername(String name);

    public List<UserDTO> getUsersByRoleName(String roleName);

    public List<UserDTO> getUsersByAgeGreaterThan(int age);

    public List<UserDTO> findByAgeLessThan(int age);
}

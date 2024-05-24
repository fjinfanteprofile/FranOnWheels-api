package com.example.franonwheels.service;

import com.example.franonwheels.Util.CustomException;
import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.UserDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService {

    UserDTO createUser(UserDTO user) throws CustomException;

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> updateUser(UserDTO userDTO, Long id);

    public void deactivateUserById(Long id);

    public void activateUserById(Long id);

    List<UserDTO> getAdminUsers();

    public List<UserDTO> getUserByUsername(String name);

    public List<UserDTO> getUsersByRoleName(String roleName);

    public List<UserDTO> getUsersByAgeGreaterThan(int age);

    public List<UserDTO> findByAgeLessThan(int age);

    public List<UserDTO> getActiveUsers();

    public List<UserDTO> getInactiveUsers();

    public Optional<UserDTO> loginUser(String email, String password);

    public UserDTO updateUserProfile(UserDTO userDTO, Long id);
}

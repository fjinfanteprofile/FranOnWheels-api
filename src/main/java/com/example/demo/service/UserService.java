package com.example.demo.service;

import com.example.demo.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);

    List<User> getAdminUsers();
}

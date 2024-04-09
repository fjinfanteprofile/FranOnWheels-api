package com.example.demo.service;

import com.example.demo.model.domain.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private  UserRepository userRepository;


    // Create operation
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read operations
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Update operation
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete operation
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAdminUsers() {
        return userRepository.findByRoleName("ADMIN");
    }


}

package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private  UserRepository userRepository;


    // Create operation
    public UserDTO createUser(UserDTO userDTO) {

        return UserMapper.userConvertToDTO(this.userRepository.save(UserMapper.userConvertToEntity(userDTO)));
    }

    // Read operations
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserMapper::userConvertToDTO)
                    .collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserMapper::userConvertToDTO);
    }

    public UserDTO updateUser(UserDTO userDTO) {

        return UserMapper.userConvertToDTO(this.userRepository.save(UserMapper.userConvertToEntity(userDTO)));
    }


    // Delete operation
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDTO> getAdminUsers() {
        List<User> adminUsers = userRepository.findByRoleName("ADMIN");

        return adminUsers.stream()
                .map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());
    }


}

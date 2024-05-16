package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.repository.BookingsRepository;
import com.example.franonwheels.repository.ClassesRepository;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.repository.UserRepository;
import com.example.franonwheels.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookingsRepository bookingsRepository;
    private final ClassesRepository classesRepository;


    // Create operation
    public UserDTO createUser(UserDTO userDTO) {
        // Convert UserDTO to User entity
        User user = UserMapper.userConvertToEntity(userDTO);

        // Check and save associated role entity
        if (user.getRole() != null) {
            Role role = user.getRole();
            if (role.getId() == null) {
                // Role entity is not saved yet, save it first
                role = roleRepository.save(role);
            }
            user.setRole(role); // Set the entity to user
        }

        // Convert User entity to UserDTO and return
        return UserMapper.userConvertToDTO(userRepository.save(user));
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

    public Optional<UserDTO> updateUser(UserDTO userDTO, Long id) {

        // Retrieve the user entity from the repository based on the provided ID
        Optional<User> optionalUser = userRepository.findById(id);

        // Check if the user exists
        if (optionalUser.isPresent()) {
            // Map the updated fields from the UserDTO to the user entity
            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setDni(userDTO.getDni());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setAddress(userDTO.getAddress());
            user.setEmail(userDTO.getEmail());
            user.setPassword(userDTO.getPassword());
            user.setAge(userDTO.getAge());
            user.setUsername(userDTO.getUsername());
            if (userDTO.getRole() != null) {
                user.setRole(roleRepository.findById(userDTO.getRole().getId())
                        .orElseThrow(() -> new NoSuchElementException("Role not found")));
            }

            // Save the updated user entity
            User updatedUser = userRepository.save(user);

            // Convert the updated user entity to UserDTO
            UserDTO updatedUserDTO = UserMapper.userConvertToDTO(updatedUser);
            return Optional.of(updatedUserDTO);
        } else {
            // User not found
            return Optional.empty();
        }
    }

    public UserDTO updateUserProfile(UserDTO userDTO, Long id) {

        // Retrieve the user entity from the repository based on the provided ID
        Optional<User> optionalUser = userRepository.findById(id);

        // Check if the user exists
        if (optionalUser.isPresent()) {
            // Map the updated fields from the UserDTO to the user entity
            User user = optionalUser.get();
            user.setName(userDTO.getName());
            user.setLastName(userDTO.getLastName());
            user.setDni(userDTO.getDni());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setAddress(userDTO.getAddress());
            user.setEmail(userDTO.getEmail());

            // Save the updated user entity
            User updatedUser = userRepository.save(user);

            // Convert the updated user entity to UserDTO
            UserDTO updatedUserDTO = UserMapper.userConvertToDTO(updatedUser);
            return updatedUserDTO;
        } else {
            // User not found
            return null;
        }
    }



    public List<UserDTO> getAdminUsers() {
        List<User> adminUsers = userRepository.findByRoleNameIgnoreCaseContaining("ADMIN");

        return adminUsers.stream()
                .map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUserByUsername(String name) {

        List<User> user = userRepository.findByNameIgnoreCaseContaining(name);
        return user.stream().
                map(UserMapper::userConvertToDTO)
               .collect(Collectors.toList());

    }

    public List<UserDTO> getUsersByRoleName(String roleName) {
        List<User> users = userRepository.findByRoleNameIgnoreCaseContaining(roleName);
        return users.stream().
                map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getUsersByAgeGreaterThan(int age) {

        List<User> users = userRepository.findByAgeGreaterThan(age);
        return users.stream().
                map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());

    }

    public List<UserDTO> findByAgeLessThan(int age) {

        List<User> users = userRepository.findByAgeLessThan(age);
        return users.stream().
                map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());

    }
    public void deactivateUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setActive(0);
            userRepository.save(user);
        });
    }

    public void activateUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.ifPresent(user -> {
            user.setActive(1);
            userRepository.save(user);
        });
    }
    public List<UserDTO> getActiveUsers() {
        List<User> activeUsers = userRepository.findByActive(1); // Assuming active flag is 1 for active users
        return activeUsers.stream()
                .map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());
    }

    public List<UserDTO> getInactiveUsers() {
        List<User> inactiveUsers = userRepository.findByActive(0); // Assuming active flag is 0 for inactive users
        return inactiveUsers.stream()
                .map(UserMapper::userConvertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> loginUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && password.equals(user.get().getPassword())) {
            return Optional.of(UserMapper.userConvertToDTO(user.get()));
        }
        return Optional.empty();
    }

}

package com.example.franonwheels.service.impl;

import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.repository.BookingsRepository;
import com.example.franonwheels.repository.ClassesRepository;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.repository.SpecialityRepository;
import com.example.franonwheels.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SpecialityRepository specialityRepository;
    private final RoleRepository roleRepository;
    private final BookingsRepository bookingsRepository;
    private final ClassesRepository classesRepository;


    // Create operation
    public UserDTO createUser(UserDTO userDTO) {

        return userMapper.userConvertToDTO(this.userRepository.save(userMapper.userConvertToEntity(userDTO)));
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
        // Check if the provided UserDTO has a valid ID
        if (userDTO.getId() == null) {
            throw new IllegalArgumentException("User ID is required for update");
        }

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
            // Update role and speciality if needed
            if (userDTO.getRole() != null) {
                user.setRole(roleRepository.findById(userDTO.getRole().getId())
                        .orElseThrow(() -> new NoSuchElementException("Role not found")));
            }
            if (userDTO.getSpeciality() != null) {
                user.setSpeciality(specialityRepository.findById(userDTO.getSpeciality().getId())
                        .orElseThrow(() -> new NoSuchElementException("Speciality not found")));
            }

            // Save the updated user entity
            User updatedUser = userRepository.save(user);

            // Convert the updated user entity to UserDTO
            UserDTO updatedUserDTO = userMapper.userConvertToDTO(updatedUser);
            return Optional.of(updatedUserDTO);
        } else {
            // User not found
            return Optional.empty();
        }
    }

    // Delete operation
    @Transactional
    public void deleteUserById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            bookingsRepository.deleteBookingsByUserId(user.getId());
            classesRepository.deleteClassesByUserId(user.getId());

            userRepository.deleteById(id);

        } else {
            throw new NoSuchElementException("User not found");
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
}

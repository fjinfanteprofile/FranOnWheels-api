package com.example.franonwheels.Util;

import com.example.franonwheels.model.domain.Role;
import com.example.franonwheels.model.domain.Speciality;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.model.dtos.RoleDTO;
import com.example.franonwheels.model.dtos.SpecialityDTO;
import com.example.franonwheels.model.dtos.UserDTO;
import com.example.franonwheels.repository.RoleRepository;
import com.example.franonwheels.repository.SpecialityRepository;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
@Component
public class UserMapper {

    private static RoleRepository roleRepository;
    private static SpecialityRepository specialityRepository;

    public UserMapper(RoleRepository roleRepository , SpecialityRepository specialityRepository) {
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
    }

    public static UserDTO userConvertToDTO(User user){
        if (user == null){
            throw new NoSuchElementException("User provided is null");
        }
        RoleDTO roleDTO = null;
        if (user.getRole() != null) {
            roleDTO = RoleDTO.builder()
                    .id(user.getRole().getId())
                    .name(user.getRole().getName())
                    .build();
        }

        SpecialityDTO specialityDTO = null;
        if (user.getSpeciality() != null) {
            specialityDTO = SpecialityDTO.builder()
                    .id(user.getSpeciality().getId())
                    .name(user.getSpeciality().getName())
                    .build();
        }
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .dni(user.getDni())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .role(roleDTO)
                .speciality(specialityDTO)
                .build();
    }

    public static User userConvertToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("UserDTO provided is null");
        }

        User user = User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .age(userDTO.getAge())
                .dni(userDTO.getDni())
                .password(userDTO.getPassword())
                .build();

        // Convert RoleDTO to Role entity and set it on the User entity
        if (userDTO.getRole() != null) {
            RoleDTO roleDTO = userDTO.getRole();
            if (roleDTO.getId() != null) {
                Optional<Role> optionalRole = roleRepository.findById(roleDTO.getId());
                if (optionalRole.isPresent()) {
                    user.setRole(optionalRole.get());
                } else {
                    throw new NoSuchElementException("Role with ID " + roleDTO.getId() + " not found");
                }
            } else {
                throw new IllegalArgumentException("Role ID is not provided");
            }
        }

        // Convert SpecialityDTO to Speciality entity and set it on the User entity
        if (userDTO.getSpeciality() != null) {
            SpecialityDTO specialityDTO = userDTO.getSpeciality();
            if (specialityDTO.getId() != null) {
                Optional<Speciality> optionalSpeciality = specialityRepository.findById(specialityDTO.getId());
                if (optionalSpeciality.isPresent()) {
                    user.setSpeciality(optionalSpeciality.get());
                } else {
                    throw new NoSuchElementException("Speciality with ID " + specialityDTO.getId() + " not found");
                }
            } else {
                throw new IllegalArgumentException("Speciality ID is not provided");
            }
        }

        return user;
    }

    private static RoleDTO roleConvertToDTO(Role role) {
        if (role == null) {
            return null;
        }
        return RoleDTO.builder()
                .name(role.getName())
                .build();
    }
    private static Speciality specialityConvertToEntity(SpecialityDTO specialityDTO) {
        if (specialityDTO == null) {
            return null;
        }
        return Speciality.builder()
                .name(specialityDTO.getName())
                .build();
    }
    private static Role roleConvertToEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        return Role.builder()
                .name(roleDTO.getName())
                .build();
    }
    private static SpecialityDTO specialityConvertToDTO(Speciality speciality) {
        if (speciality == null) {
            return null;
        }
        return SpecialityDTO.builder()
                .name(speciality.getName())
                .build();
    }
}

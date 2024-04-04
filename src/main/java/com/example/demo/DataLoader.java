package com.example.demo;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.example.demo.model.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.domain.Role;
import com.example.demo.model.domain.Speciality;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SpecialityRepository;

@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpecialityRepository specialityRepository;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, SpecialityRepository specialityRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
    }

    @PostConstruct
    public void init() {
        // Create roles and specialities if not exist
        createRoles();
        createSpecialities();

        // Load one of the roles and specialities created
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Speciality specialityA1 = specialityRepository.findByName("A1");
        //Loaded other role and speciality for the second user
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Speciality specialityA2 = specialityRepository.findByName("A2");

        // Insert users if the repository is empty
        if (userRepository.count() == 0) {
            insertUsers(roleUser, roleAdmin, specialityA1, specialityA2);
        }
    }

    private void createRoles() {
        // Check if roles already exist in the database
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));
        }
    }

    private void createSpecialities() {
        // Check if specialities already exist in the database
        if (specialityRepository.count() == 0) {
            specialityRepository.save(new Speciality("A1"));
            specialityRepository.save(new Speciality("A2"));
        }
    }

    private void insertUsers(Role roleUser, Role roleAdmin, Speciality specialityA1, Speciality specialityA2) {
        // Create and save users
        User user1 = User.builder()
                .name("John")
                .lastName("Doe")
                .dni("12345678")
                .email("john@example.com")
                .address("123 Street")
                .phoneNumber("123456789")
                .role(roleUser)
                .speciality(specialityA1)
                .build();

        userRepository.save(user1);

        User user2 = User.builder()
                .name("Antonio")
                .lastName("Yomama")
                .dni("12345678")
                .email("fran@example.com")
                .address("1222 Street")
                .phoneNumber("1234567222")
                .role(roleAdmin)
                .speciality(specialityA2)
                .build();
        userRepository.save(user2);
    }
}

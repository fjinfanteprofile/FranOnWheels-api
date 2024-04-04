package com.example.demo;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import com.example.demo.model.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.domain.Role;
import com.example.demo.model.domain.Speciality;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.SpecialityRepository;

/**
 * Component responsible for loading initial data into the application.
 */
@Component
public class DataLoader {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SpecialityRepository specialityRepository;


//      Constructor for DataLoader class.
//      Initializes the repositories required for data loading.

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, SpecialityRepository specialityRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.specialityRepository = specialityRepository;
    }

    /**
     * Method annotated with @PostConstruct to be executed after the bean is initialized.
     * It initializes the data loading process by creating roles, specialities, and inserting users if the repository is empty.
     */
    @PostConstruct
    public void init() {
        // Create roles and specialities if they do not exist
        createRoles();
        createSpecialities();

        // Load one of the roles and specialities created
        Role roleUser = roleRepository.findByName("ROLE_USER");
        Speciality specialityA1 = specialityRepository.findByName("A1");
        // Load another role and speciality for the second user
        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Speciality specialityA2 = specialityRepository.findByName("A2");

        // Insert users if the repository is empty
        if (userRepository.count() == 0) {
            insertUsers(roleUser, roleAdmin, specialityA1, specialityA2);
        }
        // Retrieve and print users
        printUsers();
        deleteUser();
    }


//      Creates and saves roles if they do not exist in the database.

    private void createRoles() {
        // Check if roles already exist in the database
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_USER")); // Save ROLE_USER
            roleRepository.save(new Role("ROLE_ADMIN")); // Save ROLE_ADMIN
        }
    }

//
//      Creates and saves specialities if they do not exist in the database.
//
    private void createSpecialities() {
        // Check if specialities already exist in the database
        if (specialityRepository.count() == 0) {
            specialityRepository.save(new Speciality("A1")); // Save Speciality A1
            specialityRepository.save(new Speciality("A2")); // Save Speciality A2
        }
    }


//      Inserts users into the database with specified roles and specialities.
//      Two users are inserted, one with ROLE_USER and Speciality A1, and another with ROLE_ADMIN and Speciality A2.

    private void insertUsers(Role roleUser, Role roleAdmin, Speciality specialityA1, Speciality specialityA2) {
        // Create and save users
        User user1 = User.builder()
                .name("John")
                .lastName("Doe")
                .dni("12345678")
                .email("john@example.com")
                .address("123 Street")
                .phoneNumber("123456789")
                .role(roleUser) // Assign ROLE_USER
                .speciality(specialityA1) // Assign Speciality A1
                .build();

        userRepository.save(user1); // Save user1

        User user2 = User.builder()
                .name("Antonio")
                .lastName("Yomama")
                .dni("12345678")
                .email("fran@example.com")
                .address("1222 Street")
                .phoneNumber("1234567222")
                .role(roleAdmin) // Assign ROLE_ADMIN
                .speciality(specialityA2) // Assign Speciality A2
                .build();

        userRepository.save(user2); // Save user2
    }
    // Method to print users from the database
    private void printUsers() {
        System.out.println("Users in the database:");
        userRepository.findAll().forEach(user -> {
            String userName = user.getName();
            String roleName = user.getRole().getName();
            String specialityName = user.getSpeciality().getName();
            System.out.println("Name: " + userName + ", Role: " + roleName + ", Speciality: " + specialityName);
        });
    }

    //
    private void deleteUser() {
        long userId = 1L; // ID of the user to delete
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            System.out.println("Deleted user with ID " + userId);
        } else {
            System.out.println("User with ID " + userId + " not found");
        }
    }

}

package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.example.demo.model.domain.User;
import com.example.demo.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert users here
        insertUsers();
    }

    private void insertUsers() {
        // Check if users already exist in the database
        if (userRepository.count() == 0) {
            // Create and save users
            User user1 = User.builder()
                    .name("John")
                    .lastName("Doe")
                    .dni("12345678")
                    .email("john@example.com")
                    .address("123 Street")
                    .phoneNumber("123456789")
                    .build();

            User user2 = User.builder()
                    .name("Jane")
                    .lastName("Smith")
                    .dni("87654321")
                    .email("jane@example.com")
                    .address("456 Street")
                    .phoneNumber("987654321")
                    .build();

            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}

package com.example.franonwheels.api.repository;

import org.assertj.core.api.Assertions;
import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class FranOnWheelsTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveAll_ReturnSavedUser(){

        //Arrange
        User user = User.builder()
                .name("Fernandonwheels")
                .lastName("LastNamee")
                .dni("1234A")
                .email("user@example.com")
                .address("Addressjected")
                .phoneNumber("33333")
                .build();
        //Act
    User savedUser = userRepository.save(user);


        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);

    }


}

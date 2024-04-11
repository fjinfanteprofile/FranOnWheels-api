package com.example.franonwheels.api.repository;

import com.example.franonwheels.model.domain.User;
import com.example.franonwheels.repository.UserRepository;
import com.example.franonwheels.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)

public class UserServiceUnitTest {
    // Define a User object with sample data
    private final User savedUser = User.builder()
            .name("Fernandonwheels")
            .lastName("LastNamee")
            .dni("1234A")
            .email("user@example.com")
            .address("Addressjected")
            .phoneNumber("33333")
            .build();

    // Mock UserRepository
    @Mock
    private UserRepository userRepositoryMock;
    // Inject mocked dependencies into UserServiceImpl
    @InjectMocks
    private UserServiceImpl userServiceImplMock;

    // Before all test methods, initialize mocks
    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(UserServiceUnitTest.class);
    }

    // Test case: when a user is added, then the saved user should be returned
    @Test
    public void whenUserAdded_thenReturnSavedUser() {

        //define the behavior of userRepositoryMock.save() method
        Mockito.when(this.userRepositoryMock.save(Mockito.any(User.class))).thenReturn(savedUser);
        // Call the method being tested
        this.userServiceImplMock.createUser(savedUser);
        // Verify that userRepositoryMock.save() was called exactly once with any User object
        Mockito.verify(this.userRepositoryMock, Mockito.times(1))
                .save(Mockito.any(User.class));
    }


}

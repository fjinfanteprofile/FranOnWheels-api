package com.example.franonwheels.api.repository;

import com.example.franonwheels.Util.UserMapper;
import com.example.franonwheels.model.domain.Role;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)

public class UserServiceUnitTest {

    //roles
    private final Role admin = Role.builder()
            .id(1L)
            .name("ADMIN")
            .build();

    private final Role user = Role.builder()
            .id(2L)
            .name("USER")
            .build();


    // Define a list of users
    private final List<User> userList = Arrays.asList(
            User.builder()
                    .id(1L)
                    .name("Fernandonwheels")
                    .lastName("LastNamee")
                    .dni("1234A")
                    .email("user@example.com")
                    .address("Addressjected")
                    .role(admin)
                    .phoneNumber("33333")
                    .build(),
            User.builder()
                    .id(2L)
                    .name("a")
                    .lastName("a")
                    .dni("1234Aa")
                    .email("user@example.comaa")
                    .address("aa")
                    .role(user)
                    .phoneNumber("1")
                    .build(),
            User.builder()
                    .id(3L)
                    .name("b")
                    .lastName("b")
                    .dni("1234Ab")
                    .email("user@example.combb")
                    .address("b")
                    .role(user)
                    .phoneNumber("2")
                    .build());


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
        Mockito.when(this.userRepositoryMock.save(Mockito.any(User.class))).thenReturn(userList.get(0)); //used when taking arguments (Mokito.any)
        // Call the method being tested
        this.userServiceImplMock.createUser(UserMapper.userConvertToDTO(userList.get(0)));
        // Verify that userRepositoryMock.save() was called exactly once with any User object
        Mockito.verify(this.userRepositoryMock, Mockito.times(1))
                .save(Mockito.any(User.class));
    }

    @Test
    public void whenFindAllUsers_thenReturnListOfUsers() {

        Mockito.when(this.userRepositoryMock.findAll()).thenReturn(userList); //used when not taking arguments

        this.userServiceImplMock.getAllUsers();

        Mockito.verify(this.userRepositoryMock, Mockito.times(1)).findAll();

    }

    @Test
    public void whenFindUserById_thenReturnOptionalUser() {

        Mockito.when(this.userRepositoryMock.findById(1L)).thenReturn(Optional.of(userList.get(0)));

        this.userServiceImplMock.getUserById(1L);

        Mockito.verify(this.userRepositoryMock, Mockito.times(1)).findById(1L);

    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser() {

        Mockito.when(this.userRepositoryMock.save(Mockito.any(User.class))).thenReturn(userList.get(0));

        this.userServiceImplMock.updateUser(UserMapper.userConvertToDTO(userList.get(0)), userList.get(0).getId());


        Mockito.verify(this.userRepositoryMock, Mockito.times(1)).save(Mockito.any(User.class));

    }

    @Test
    public void whenDeleteUserById_thenReturnDeletedUser() {


        Mockito.doNothing().when(this.userRepositoryMock).deleteById(1L); //use doNothing() to mock the behavior of deleteById method and indicate that it doesn't return anything (void).

        this.userServiceImplMock.deleteUserById(1L);

        Mockito.verify(this.userRepositoryMock, Mockito.times(1)).deleteById(1L);

    }

    @Test
    public void whenGetAdminUsers_thenReturnListOfAdmins() {

        List <User> adminList = userList.stream()
                .filter(user -> user.getRole().getName().equals("ADMIN"))
                .collect(Collectors.toList()); //terminal operation that collects the filtered elements of the stream into a new list.

        Mockito.when(this.userRepositoryMock.findByRoleNameIgnoreCaseContaining("ADMIN")).thenReturn(adminList);

        this.userServiceImplMock.getAdminUsers();

        Mockito.verify(this.userRepositoryMock, Mockito.times(1)).findByRoleNameIgnoreCaseContaining("ADMIN");

    }



}

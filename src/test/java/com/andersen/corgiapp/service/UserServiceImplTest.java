package com.andersen.corgiapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.FieldLengthExceedException;
import com.andersen.corgiapp.exception.NegativeAgeException;
import com.andersen.corgiapp.exception.RequiredFieldIsEmptyException;
import com.andersen.corgiapp.repository.UserRepository;

public class UserServiceImplTest {

    private UserService userService;
    private UserRepository userRepository;

    @BeforeEach
    void beforeAll() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void saveUser() {
        User user = new User("Ivan", "Ivanov", 18);
        User createdUser = new User(1, "Ivan", "Ivanov", 18);
        Mockito.when(userRepository.save(user)).thenReturn(createdUser);
        userService.save(user);
    }

    @Test
    void saveUserWithEmptyName() {
        User user = new User("", "Ivanov", 18);
        Assertions.assertThrows(RequiredFieldIsEmptyException.class, () -> userService.save(user));
    }

    @Test
    void saveUserWithNegativeAge() {
        User user = new User("Ivan", "Ivanov", -1);
        Assertions.assertThrows(NegativeAgeException.class, () -> userService.save(user));
    }

    @Test
    void saveUserWithLargeSurname() {
        User user = new User();
        user.setName("Ivan");
        user.setSurname("dN8g1AaVxPjkKMY46Lh9cdWryJC5uXattOFiPmYBZYGVK8OTzTMNS6Lq8iKwfvpxn5nkibczCoHxhy7gPKSZNddsLCmHkIQ2JTEZgs");
        user.setAge(18);
        Assertions.assertThrows(FieldLengthExceedException.class, () -> userService.save(user));
    }
}

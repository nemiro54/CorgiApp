package com.andersen.corgiapp.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.FieldLengthExceedException;
import com.andersen.corgiapp.exception.NegativeAgeException;
import com.andersen.corgiapp.exception.RequiredFieldIsEmptyException;

public class UserServiceImplTest {

    private final UserService userService = new UserServiceImpl();

    @Test
    void saveUser() {
        User user = new User("Ivan", "Ivanov", 18);
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

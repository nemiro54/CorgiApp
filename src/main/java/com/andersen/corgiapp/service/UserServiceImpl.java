package com.andersen.corgiapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.FieldLengthExceedException;
import com.andersen.corgiapp.exception.NegativeAgeException;
import com.andersen.corgiapp.exception.RequiredFieldIsEmptyException;
import com.andersen.corgiapp.repository.UserRepository;

public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private static final int FIELD_MAX_LENGTH = 100;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(User user) {
        validate(user);
        user = userRepository.saveUser(user);
        log.info("Successfully created user with id {}", user.getId());
    }

    @Override
    public User find(long userId) {
        User user = userRepository.getUser(userId);
        log.info("Successfully found user with id {}", userId);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = userRepository.getAllUsers();
        log.info("Successfully showed all users");
        return users;
    }

    @Override
    public void update(User user) {
        User oldUser = userRepository.getUser(user.getId());
        validate(user);

        userRepository.updateUser(user);
        log.info("Successfully updated user with id {}", oldUser.getId());
    }

    @Override
    public void delete(long userId) {
        User user = userRepository.getUser(userId);
        userRepository.deleteUser(user.getId());
        log.info("Successfully deleted user with id {}", user.getId());
    }

    private void validate(User user) {
        if (user.getName() == null || user.getName().isBlank()) {
            throw new RequiredFieldIsEmptyException(String.format("Required field is empty. Name: %s", user.getName()));
        }

        if (user.getName().length() > FIELD_MAX_LENGTH) {
            throw new FieldLengthExceedException(String.format("Name length is greater than %d", FIELD_MAX_LENGTH));
        }

        if (user.getSurname() == null || user.getSurname().isBlank()) {
            throw new RequiredFieldIsEmptyException(String.format("Required field is empty. Surname: %s", user.getSurname()));
        }

        if (user.getSurname().length() > FIELD_MAX_LENGTH) {
            throw new FieldLengthExceedException(String.format("Surname length is greater than %d", FIELD_MAX_LENGTH));
        }

        if (user.getAge() < 0) {
            throw new NegativeAgeException(String.format("Age is negative. Age: %d", user.getAge()));
        }
    }
}

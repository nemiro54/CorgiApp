package com.andersen.corgiapp.service;

import com.andersen.corgiapp.connection.DataSource;
import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.EntityNotFoundException;
import com.andersen.corgiapp.exception.NoConnectionException;
import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryTest {

    Connection connection;
    UserRepository userRepository;

    @BeforeEach
    public void before() {
        try {
            Path path = Path.of("src/main/resources/schema.sql");
            String table = Files.readString(path);
            this.connection = DataSource.getConnection();
            this.userRepository = new UserRepositoryImpl();
            PreparedStatement statement = connection.prepareStatement(table);
            statement.execute();
        } catch (SQLException | IOException e) {
            throw new NoConnectionException("Can't get database connection", e);
        }
    }

    @Test
    public void saveUser() {
        User user = new User("Peter", "Parker", 18);
        userRepository.saveUser(user);
        long userId = user.getId();
        Assertions.assertEquals(userRepository.getUser(userId), user);
    }

    @Test
    public void getUser() {
        User user = new User("Peter", "Parker", 18);
        userRepository.saveUser(user);
        long userId = user.getId();
        Assertions.assertNotNull(userRepository.getUser(userId));
    }

    @Test
    public void deleteUser() {
        User user = new User("Peter", "Parker", 18);
        userRepository.saveUser(user);
        long userId = user.getId();
        userRepository.deleteUser(userId);
        Assertions.assertThrows(EntityNotFoundException.class, () -> userRepository.getUser(userId));
    }

    @Test
    public void getAll() {
        User user = new User("Peter", "Parker", 18);
        userRepository.saveUser(user);
        List<User> users = userRepository.getAllUsers();
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    public void updateUser() {
        User user = new User("Peter", "Parker", 18);
        userRepository.saveUser(user);
        long userId = user.getId();
        User updatedUser = new User(userId, "Steven", "Strange", 40);
        userRepository.updateUser(updatedUser);
        User userDb = userRepository.getUser(userId);
        Assertions.assertEquals(userDb, updatedUser);
    }

    @AfterEach
    public void after() throws SQLException {
        this.connection.close();
    }
}
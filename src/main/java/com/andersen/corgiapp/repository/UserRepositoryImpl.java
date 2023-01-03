package com.andersen.corgiapp.repository;

import com.andersen.corgiapp.connection.DataSource;
import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.EntityNotFoundException;
import com.andersen.corgiapp.exception.QueryExecutionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private static final String QUERY_FOR_SAVING = "INSERT INTO users(name, surname, age) VALUES (?, ?, ?)";
    private static final String QUERY_FOR_SINGLE_USER = "SELECT * FROM users WHERE id = ?";
    private static final String QUERY_FOR_ALL_USERS = "SELECT * FROM users";
    private static final String QUERY_FOR_UPDATE = "UPDATE users SET name = ?, surname = ?, age = ? WHERE id = ?";
    private static final String QUERY_FOR_DELETE = "DELETE FROM users WHERE id = ?";

    @Override
    public User saveUser(User user) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_SAVING,
                     Statement.RETURN_GENERATED_KEYS)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new QueryExecutionException(String.format("Can't save user. No rows affected. User: %s", user));
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }

            connection.commit();

            return user;
        } catch (SQLException e) {
            throw new QueryExecutionException(String.format("Can't save user. No rows affected. User: %s", user));
        }
    }

    @Override
    public User getUser(long userId) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_SINGLE_USER)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            statement.setLong(1, userId);
            ResultSet res = statement.executeQuery();

            if (res.next()) {
                User user = mapRowToUser(res);
                connection.commit();
                return user;
            } else {
                throw new EntityNotFoundException(userId, User.class.getSimpleName());
            }
        } catch (SQLException e) {
            throw new QueryExecutionException(String.format("Wrong id: %s", userId));
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_ALL_USERS)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            ResultSet res = statement.executeQuery();

            List<User> users = new ArrayList<>();
            while (res.next()) {
                users.add(mapRowToUser(res));
            }

            connection.commit();

            return users;
        } catch (SQLException e) {
            throw new QueryExecutionException("Can't get all employees");
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_UPDATE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setLong(4, user.getId());
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new QueryExecutionException(String.format("Can't update user. No rows affected. User: %s", user));
            }

            connection.commit();
        } catch (SQLException e) {
            throw new QueryExecutionException(String.format("Can't update user. No rows affected. User: %s", user));
        }
    }

    @Override
    public void deleteUser(long userId) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(QUERY_FOR_DELETE)) {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            statement.setLong(1, userId);
            statement.execute();

            connection.commit();
        } catch (SQLException e) {
            throw new QueryExecutionException(String.format("Wrong id: %s", userId));
        }
    }

    private User mapRowToUser(ResultSet res) throws SQLException {
        int id = res.getInt("id");
        String name = res.getString("name");
        String surname = res.getString("surname");
        int age = res.getInt("age");
        return new User(id, name, surname, age);
    }
}
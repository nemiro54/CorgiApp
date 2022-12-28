package com.andersen.corgiapp.repository;

import com.andersen.corgiapp.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements com.andersen.corgiapp.repository.UserRepository {

    private final String url;
    private final String username;
    private final String password;

    public UserRepositoryImpl(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void save(User user) {
        String sql = String.format("INSERT INTO users(name, surname, age) VALUES ('%s', '%s', %s)",
                user.getName(), user.getSurname(), user.getAge());
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            int res = statement.executeUpdate(sql);
            System.out.println("RES - " + res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User get(long userId) {
        String sql = String.format("SELECT * FROM users WHERE id = %s", userId);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(sql);
            List<User> users = mapRowToList(res);
            return users.get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll() {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM users");
            return mapRowToList(res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(User user) {
        String sql = String.format("UPDATE users SET name = '%s', surname = '%s', age = %s WHERE id = %s",
                user.getName(), user.getSurname(), user.getAge(), user.getId());
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(long userId) {
        String sql = String.format("DELETE FROM users WHERE id = %s", userId);
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> mapRowToList(ResultSet res) throws SQLException {
        List<User> users = new ArrayList<>();
        while (res.next()) {
            int id = res.getInt("id");
            String name = res.getString("name");
            String surname = res.getString("surname");
            int age = res.getInt("age");
            User user = new User(id, name, surname, age);
            users.add(user);
        }
        return users;
    }
}

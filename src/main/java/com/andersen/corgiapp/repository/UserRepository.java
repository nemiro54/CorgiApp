package com.andersen.corgiapp.repository;

import com.andersen.corgiapp.entity.User;

import java.util.List;

public interface UserRepository {

    User saveUser(User user);

    User getUser(long userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(long userId);
}

package com.andersen.corgiapp.repository;

import com.andersen.corgiapp.entity.User;

import java.util.List;

public interface UserRepository {

    void save(User user);

    User get(long userId);

    List<User> getAll();

    void update(User user);

    void delete(long userId);
}

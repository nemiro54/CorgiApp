package com.andersen.corgiapp.service;

import java.util.List;

import com.andersen.corgiapp.entity.User;

public interface UserService {

    /**
     * Save provided {@link User} entity.
     * @param user provide {@link User}
     */
    void save(User user);

    /**
     * Find all users {@link User}
     * @param userId provided User ID
     * @return {@link User} with provided User ID
     */
    User find(long userId);

    /**
     * Find all users {@link User}
     */
    List<User> findAll();

    /**
     * Update user {@link User}
     * @param user provide {@link User}
     */
    void update(User user);

    /**
     * Delete user {@link User}
     * @param userId provide id for {@link User}
     */
    void delete(long userId);
}

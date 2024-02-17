package com.example.interfaces.dao;

import com.example.implementation.User;

import java.util.List;

public interface UserDAO {

    User createUser(User user);
    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);
    List<User> getAllUsers();
    User updateUser(User user);
    boolean deleteUser(long userId);
}

package com.example.interfaces.services;

import com.example.implementation.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);
    List<User> getAllUsers();
    User updateUser(User user);
    boolean deleteUser(long userId);
}

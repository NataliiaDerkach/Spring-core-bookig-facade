package com.example.implementation.services;

import com.example.implementation.User;
import com.example.interfaces.dao.UserDAO;
import com.example.interfaces.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User createUser(User user) {
        return userDAO.createUser(user);
    }

    @Override
    public User getUserById(long userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDAO.getUsersByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User updateUser(User user) {
       return userDAO.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userDAO.deleteUser(userId);
    }
}

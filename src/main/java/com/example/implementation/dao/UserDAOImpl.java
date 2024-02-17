package com.example.implementation.dao;

import com.example.implementation.User;
import com.example.interfaces.dao.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class UserDAOImpl implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    private final Map<Long, User> userMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public User createUser(User user) {
        if (userMap.containsKey(user.getId())) {
            throw new IllegalArgumentException("User with ID " + user.getId() + " already exists!");
        }
        user.setId(currentId);
        userMap.put(currentId, user);
        currentId++;
        logger.info("User is created: " + user);
        return user;
    }

    @Override
    public User getUserById(long userId) {
        User userById = userMap.get(userId);
        if (userById != null) {
            logger.info("Found User by ID: {}", userId);
        } else {
            logger.warn("User not found with ID: {}", userId);
        }
        return userById;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : userMap.values()) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                logger.info("Found User with email: " + user);
                return user;
            }
        }
        throw new NoSuchElementException("User with email " + email + " not found.");
    }

    @Override
    public List<User> getUsersByName(String name) {
        List<User> userByName = new ArrayList<>();
        for (User user : userMap.values()) {
            if (user.getName().equalsIgnoreCase(name)) {
                userByName.add(user);
                logger.info("Found User with name: " + user);
            }
            return userByName;
        }
        throw new NoSuchElementException("User with name " + name + " not found.");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>(userMap.values());
        logger.info("List of all Users: " + users);
        return users;
    }

    @Override
    public User updateUser(User user) {
        User userUpdate = null;
        try {
            userUpdate = userMap.put(user.getId(), user);
            logger.info("Successfully updated User : {}", user);
        } catch (Exception e) {
            logger.error("Error updating User: {}", user, e);
        }
        return userUpdate;
    }

    @Override
    public boolean deleteUser(long userId) {
        logger.info("Attempting to delete user with id: {}", userId);
        try {
            userMap.remove(userId);
            logger.info("Successfully deleted user with id: {}", userId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting user with id: {}", userId, e);
            return false;
        }
    }
}

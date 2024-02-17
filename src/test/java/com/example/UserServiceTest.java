package com.example;

import com.example.implementation.User;
import com.example.interfaces.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = context.getBean("userService", UserService.class);

    long userId;

    String createdUserEmail = "new.user@example.com";

    @BeforeEach
    void setUp() {
        userId = 3L;
        userService.createUser(new User(userId, "NewUser", createdUserEmail));
        logger.info("List of users: " + userService.getAllUsers());
    }

    @Test
    void updateUserSuccess() {
        User updatedUser = new User(userId, "UpdateUser", "update.user@example.com");
        userService.updateUser(updatedUser);

        User resultUser = userService.getUserById(userId);
        logger.info("Updated user: " + resultUser);
        assertEquals("UpdateUser", resultUser.getName(), "User name should be updated.");
        assertEquals("update.user@example.com", resultUser.getEmail(), "User email should be updated.");
    }

    @Test
    void createUserNotSuccess() {
        User alreadyExistUserById = new User(2L, "AlreadyExist", "already.exist@example.com");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(alreadyExistUserById);
        });

        String expectedMessage = "User with ID " + alreadyExistUserById.getId() + " already exists!";
        String actualMessage = exception.getMessage();
        logger.warn(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage), "Wrong message!");
    }

    @Test
    void deleteUserSuccess() {
        userService.deleteUser(userId);
        User userDelited = userService.getUserById(userId);

        assertNull(userDelited, "User should not exist");
    }

    @Test
    void findUserByEmail(){
       User foundUserByEmail= userService.getUserByEmail(createdUserEmail);

        assertEquals("new.user@example.com", foundUserByEmail.getEmail(), "User with email should be exist.");
    }

}

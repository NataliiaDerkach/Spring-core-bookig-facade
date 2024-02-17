package com.example;

import com.example.implementation.Event;
import com.example.implementation.User;
import com.example.interfaces.BookingFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class BookingFacadeIntegrTest {

    private static final Logger logger = LoggerFactory.getLogger(BookingFacadeIntegrTest.class);

    private User user;

    private Event event;

    private int seat;

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    BookingFacade bookingFacade = context.getBean("bookingFacade", BookingFacade.class);

    @BeforeEach
    void setUp() {
        user = new User(3L, "UserFacade", "user.facade@example.com");
        event = new Event(3L, "Dance", "City centre");
    }


    @Test
    void createUser() {
        bookingFacade.createUser(user);

        User resultUser = bookingFacade.getUserById(user.getId());
        logger.info("Created user: " + resultUser);
        assertEquals("UserFacade", resultUser.getName(), "User name should be: UserFacade!");
        assertEquals("user.facade@example.com", resultUser.getEmail(), "User email should be: user.facade@example.com!");
    }

    @Test
    void findEventById() {
        bookingFacade.createEvent(event);
        Event createdEvent = bookingFacade.getEventById(event.getId());

        assertEquals("Dance", createdEvent.getName(), "Event title should be: " + event.getName());
        assertEquals("City centre", createdEvent.getLocation(), "Event location should be: " + event.getLocation());
    }

    @Test
    void bookTicketTwice() {
        seat = 999;
        bookingFacade.createUser(user);
        bookingFacade.createEvent(event);

        bookingFacade.bookTicket(user.getId(), event.getId(), seat);

        Exception exception = assertThrows(Exception.class, () ->
        bookingFacade.bookTicket(1L, event.getId(), seat),
                "Expected to throw, but it didn't"
        );

        String expectedMessage = "The seat " + seat + " for event " + event.getId() + " is already booked.";
        String actualMessage = exception.getMessage();
        logger.error("Error: " + actualMessage);
        assertTrue(actualMessage.contains(expectedMessage), "Error message should contain: " + expectedMessage);
    }

}

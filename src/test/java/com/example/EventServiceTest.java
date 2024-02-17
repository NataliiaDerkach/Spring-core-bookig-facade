package com.example;

import com.example.implementation.Event;
import com.example.interfaces.services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    EventService eventService = context.getBean("eventService", EventService.class);

    long eventId;

    @BeforeEach
    void setUp() {
        eventId = 3L;
        eventService.createEvent(new Event(eventId, "Dance", "City centre"));
        logger.info("List of events: " + eventService.getAllEvents());
    }

    @Test
    void updateEventSuccess() {
        Event updatedEvent = new Event(eventId, "Skying", "Mountain resort");
        eventService.updateEvent(updatedEvent);

        Event resultEvent = eventService.getEventById(eventId);
        logger.info("List of events: " + eventService.getAllEvents());
        assertEquals("Skying", resultEvent.getName(), "Event title should be updated.");
        assertEquals("Mountain resort", resultEvent.getLocation(), "Event place should be updated.");
    }

    @Test
    void createEventNotSuccess() {
        Event alreadyExistEventById = new Event(2L, "Update", "Update location");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            eventService.createEvent(alreadyExistEventById);
        });

        String expectedMessage = "Event with ID " + alreadyExistEventById.getId() + " already exists!";
        String actualMessage = exception.getMessage();
        logger.warn(expectedMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void deleteEventSuccess() {
        eventService.deleteEvent(eventId);
        Event eventDelited = eventService.getEventById(eventId);

        assertNull(eventDelited, "Event should not exist");
    }
}

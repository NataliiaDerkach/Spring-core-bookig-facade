package com.example;

import com.example.implementation.Event;
import com.example.implementation.Ticket;
import com.example.implementation.User;
import com.example.interfaces.services.EventService;
import com.example.interfaces.services.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TicketServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceTest.class);

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    TicketService ticketService = context.getBean("ticketService", TicketService.class);
    EventService eventService = context.getBean("eventService", EventService.class);

    private final int seat = 77;

    private User user;

    private Event event;

    @BeforeEach
    void setUp() {
        user = new User(4L, "userTicket", "user.ticket@example.com");
        event = new Event(4L, "Party", "Yacht club");
    }

    @Test
    void bookTicket() {

        Ticket bookedTicket = ticketService.bookTicket(user.getId(), event.getId(), seat);

        assertNotNull(bookedTicket, "The ticket should not be null.");
        assertEquals(user.getId(), bookedTicket.getUserId(), "User IDs do not match.");
        assertEquals(event.getId(), bookedTicket.getEventId(), "Event IDs do not match.");
        assertEquals(seat, bookedTicket.getSeat(), "Seat numbers do not match.");
    }

    @Test
    void bookTicketNotSuccessfully() {
        int place = 42;
        Ticket foundTicket = null;
        Event eventFromFile = eventService.getEventById(1L);

        List<Ticket> bookedTickets = ticketService.getBookedTickets(eventFromFile);
        Optional<Ticket> existTicketFromFile = bookedTickets.stream()
                .filter(ticket -> ticket.getEventId() == eventFromFile.getId() && ticket.getSeat() == place)
                .findFirst();

        if (existTicketFromFile.isPresent()) {
            foundTicket = existTicketFromFile.get();
        } else {
            logger.error("Ticket not found!");
        }


        Ticket finalFoundTicket = foundTicket;
        Exception exception = assertThrows(Exception.class, () ->
                        ticketService.bookTicket(user.getId(), finalFoundTicket.getEventId(), finalFoundTicket.getSeat()),
                "Expected to throw, but it didn't"
        );

        String expectedMessage = "The seat " + finalFoundTicket.getSeat() + " for event " + finalFoundTicket.getEventId() + " is already booked.";
        String actualMessage = exception.getMessage();
        logger.error(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage), "Error message should contain: " + expectedMessage);

    }

}

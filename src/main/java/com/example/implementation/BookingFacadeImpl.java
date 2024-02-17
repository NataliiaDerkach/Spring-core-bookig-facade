package com.example.implementation;

import com.example.interfaces.BookingFacade;
import com.example.interfaces.services.EventService;
import com.example.interfaces.services.TicketService;
import com.example.interfaces.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingFacadeImpl implements BookingFacade {


    private final UserService userService;


    private final EventService eventService;


    private final TicketService ticketService;

    @Autowired
    public BookingFacadeImpl(UserService userService, EventService eventService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.ticketService = ticketService;
    }

    @Override
    public Event getEventById(long eventId) {
        return eventService.getEventById(eventId);
    }

    @Override
    public Event createEvent(Event event) {
        return eventService.createEvent(event);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventService.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventService.deleteEvent(eventId);
    }

    @Override
    public User getUserById(long userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userService.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userService.getUsersByName(name);
    }

    @Override
    public User createUser(User user) {
        return userService.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userService.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userService.deleteUser(userId);
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int place) {
        return ticketService.bookTicket(userId, eventId, place);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getBookedTickets(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        return ticketService.getBookedTickets(event);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        return ticketService.cancelTicket(ticketId);
    }
}

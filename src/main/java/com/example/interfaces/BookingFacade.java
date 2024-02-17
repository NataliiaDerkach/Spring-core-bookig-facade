package com.example.interfaces;

import com.example.implementation.Ticket;
import com.example.implementation.User;
import com.example.implementation.Event;

import java.util.Date;
import java.util.List;

public interface BookingFacade {

    Event getEventById(long eventId);

    Event createEvent(Event event);

    Event updateEvent(Event event);


    boolean deleteEvent(long eventId);


    User getUserById(long userId);


    User getUserByEmail(String email);


    List<User> getUsersByName(String name);

    User createUser(User user);

    User updateUser(User user);


    boolean deleteUser(long userId);


    Ticket bookTicket(long userId, long eventId, int place);


    List<Ticket> getBookedTickets(User user);


    List<Ticket> getBookedTickets(Event event);


    boolean cancelTicket(long ticketId);

}

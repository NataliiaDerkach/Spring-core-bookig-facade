package com.example.implementation.dao;

import com.example.implementation.Event;
import com.example.implementation.Ticket;
import com.example.implementation.User;
import com.example.interfaces.dao.TicketDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketDAOImpl implements TicketDAO {

    private static final Logger logger = LoggerFactory.getLogger(TicketDAOImpl.class);

    private final Map<Long, Ticket> ticketMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Ticket bookTicket(long userId, long eventId, int seat) {
        boolean isBooked = ticketMap.values().stream()
                .anyMatch(ticket -> ticket.getEventId() == eventId && ticket.getSeat() == seat);

        if (isBooked) {
            throw new IllegalStateException("The seat " + seat + " for event " + eventId + " is already booked.");
        }

        Ticket ticket = new Ticket(currentId, userId, eventId, seat);
        ticketMap.put(currentId, ticket);
        currentId++;
        logger.info("Ticket is booked successfully!: " + ticket);
        return ticket;
    }

    @Override
    public boolean cancelTicket(long ticketId) {
        logger.info("Attempting to delete ticket with id: {}", ticketId);
        try {
            ticketMap.remove(ticketId);
            logger.info("Ticket with ID {} has been successfully removed.", ticketId);
            return true;
        } catch (Exception e) {
            logger.error("Attempted to remove a ticket that does not exist. Ticket ID: {}", ticketId, e);
            return false;
        }
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        List<Ticket> userTickets = new ArrayList<>();
        logger.info("Fetching booked tickets for user: {}", user.getEmail());
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getUserId() == user.getId()) {
                userTickets.add(ticket);
            }
        }
        logger.info("Found {} booked tickets for user: {}", userTickets.size(), user.getEmail());
        return userTickets;
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        List<Ticket> eventTickets = new ArrayList<>();
        logger.info("Fetching booked tickets for event: {}", event.getName());
        for (Ticket ticket : ticketMap.values()) {
            if (ticket.getEventId() == event.getId()) {
                eventTickets.add(ticket);
            }
        }
        logger.info("Found {} booked tickets for event: {}", eventTickets.size(), event.getName());
        return eventTickets;
    }
}

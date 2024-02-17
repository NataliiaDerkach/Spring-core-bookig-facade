package com.example.implementation.services;

import com.example.implementation.Event;
import com.example.implementation.Ticket;
import com.example.implementation.User;
import com.example.interfaces.dao.TicketDAO;
import com.example.interfaces.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private TicketDAO ticketDAO;

    public TicketServiceImpl(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public Ticket bookTicket(long userId, long eventId, int seat) {
        return ticketDAO.bookTicket(userId, eventId, seat);
    }

    @Override
    public boolean cancelTicket(long ticketId) {
       return ticketDAO.cancelTicket(ticketId);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketDAO.getBookedTickets(user);
    }

    @Override
    public List<Ticket> getBookedTickets(Event event) {
        return ticketDAO.getBookedTickets(event);
    }
}

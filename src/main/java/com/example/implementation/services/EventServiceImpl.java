package com.example.implementation.services;

import com.example.implementation.Event;
import com.example.interfaces.dao.EventDAO;
import com.example.interfaces.services.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {

    private EventDAO eventDAO;

    public EventServiceImpl(EventDAO eventDAO) {
        this.eventDAO = eventDAO;
    }

    @Override
    public Event createEvent(Event event) {
        return eventDAO.createEvent(event);
    }

    @Override
    public Event getEventById(long eventId) {
        return eventDAO.getEventById(eventId);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDAO.getAllEvents();
    }

    @Override
    public Event updateEvent(Event event) {
        return eventDAO.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(long eventId) {
        return eventDAO.deleteEvent(eventId);
    }
}

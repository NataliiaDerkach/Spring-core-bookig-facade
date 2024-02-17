package com.example.interfaces.dao;

import com.example.implementation.Event;

import java.util.List;

public interface EventDAO {

    Event createEvent(Event event);
    Event getEventById(long eventId);
    List<Event> getAllEvents();
    Event updateEvent(Event event);
    boolean deleteEvent(long eventId);
}

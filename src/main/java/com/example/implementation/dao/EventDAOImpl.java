package com.example.implementation.dao;

import com.example.implementation.Event;
import com.example.interfaces.dao.EventDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDAOImpl implements EventDAO {

    private static final Logger logger = LoggerFactory.getLogger(EventDAOImpl.class);

    private final Map<Long, Event> eventMap = new HashMap<>();
    private long currentId = 1;

    @Override
    public Event createEvent(Event event) {
        if (eventMap.containsKey(event.getId())) {
            throw new IllegalArgumentException("Event with ID " + event.getId() + " already exists!");
        }
        event.setId(currentId);
        eventMap.put(currentId, event);
        currentId++;
        logger.info("Event is created: " + event);
        return event;
    }

    @Override
    public Event getEventById(long eventId) {
        Event eventById = eventMap.get(eventId);
        if (eventById != null) {
            logger.info("Found Event by ID: {}, with title: {} and location: {}", eventId, eventById.getName(), eventById.getLocation());
        } else {
            logger.warn("Event not found with ID: {}", eventId);
        }
        return eventById;
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<>(eventMap.values());
    }

    @Override
    public Event updateEvent(Event event) {
        Event eventUpdate = null;
        try {
            eventUpdate = eventMap.put(event.getId(), event);
            logger.info("Successfully updated event : {}", event);
        } catch (Exception e) {
            logger.error("Error updating event: {}", event, e);
        }
        return eventUpdate;
    }

    @Override
    public boolean deleteEvent(long eventId) {
        logger.info("Attempting to delete event with id: {}", eventId);
        try {
            eventMap.remove(eventId);
            logger.info("Successfully deleted event with id: {}", eventId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting event with id: {}", eventId, e);
            return false;
        }
    }
}

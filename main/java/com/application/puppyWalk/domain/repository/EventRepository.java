package com.application.puppyWalk.domain.repository;

import com.application.puppyWalk.domain.Event;

import java.util.List;

public interface EventRepository {

    Event getEventById(long id);

    void updateEvent(Event event);

    List<Event> getEvents();
}

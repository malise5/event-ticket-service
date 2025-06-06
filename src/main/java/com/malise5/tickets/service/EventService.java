package com.malise5.tickets.service;

import com.malise5.tickets.domain.CreateEventRequest;
import com.malise5.tickets.entity.Event;

import java.util.UUID;

public interface EventService {

    Event createEvent(UUID organizerId, CreateEventRequest createEventRequest);
}

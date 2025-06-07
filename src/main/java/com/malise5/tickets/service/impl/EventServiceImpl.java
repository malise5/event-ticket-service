package com.malise5.tickets.service.impl;

import com.malise5.tickets.domain.CreateEventRequest;
import com.malise5.tickets.entity.Event;
import com.malise5.tickets.entity.TicketType;
import com.malise5.tickets.entity.User;
import com.malise5.tickets.exceptions.UserNotFoundException;
import com.malise5.tickets.repository.EventRepository;
import com.malise5.tickets.repository.UserRepository;
import com.malise5.tickets.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    @Override
    public Event createEvent(UUID organizerId, CreateEventRequest createEventRequest) {

        User organizerById = userRepository.findById(organizerId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + organizerId + " not found"));

        Event event = new Event();
        event.setName(createEventRequest.getName());
        event.setStart(createEventRequest.getStart());
        event.setEnd(createEventRequest.getEnd());
        event.setVenue(createEventRequest.getVenue());
        event.setSalesStart(createEventRequest.getSalesStart());
        event.setSalesEnd(createEventRequest.getSalesEnd());
        event.setStatus(createEventRequest.getStatus());
        event.setOrganizer(organizerById);

        List<TicketType> ticketTypesToCreate = event.getTicketTypes().stream()
                .map(ticketType -> {
                    TicketType ticketType1 = new TicketType();
                    ticketType1.setName(ticketType.getName());
                    ticketType1.setPrice(ticketType.getPrice());
                    ticketType1.setDescription(ticketType.getDescription());
                    ticketType1.setTotalAvailable(ticketType.getTotalAvailable());
                    ticketType1.setEvent(event);
                    return ticketType1;
                }).toList();

        event.setTicketTypes(ticketTypesToCreate);

         return eventRepository.save(event);
    }
}

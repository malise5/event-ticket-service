package com.malise5.tickets.controller;

import com.malise5.tickets.domain.CreateEventRequest;
import com.malise5.tickets.dtos.CreateEventRequestDto;
import com.malise5.tickets.dtos.CreateEventResponseDto;
import com.malise5.tickets.entity.Event;
import com.malise5.tickets.mappers.EventMapper;
import com.malise5.tickets.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/event")
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    public ResponseEntity<CreateEventResponseDto> createEvent(@AuthenticationPrincipal Jwt jwt,@Valid @RequestBody CreateEventRequestDto createEventRequestDto) {

        CreateEventRequest createEventRequest = eventMapper.fromDto(createEventRequestDto);
        UUID userId = UUID.fromString(jwt.getSubject());
        Event event = eventService.createEvent(userId, createEventRequest);
        return ResponseEntity.ok(eventMapper.toDto(event));
    }


}

package com.malise5.tickets.mappers;

import com.malise5.tickets.domain.CreateEventRequest;
import com.malise5.tickets.domain.CreateTicketTypeRequest;
import com.malise5.tickets.dtos.CreateEventRequestDto;
import com.malise5.tickets.dtos.CreateEventResponseDto;
import com.malise5.tickets.dtos.CreateTicketTypeRequestDto;
import com.malise5.tickets.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto createTicketTypeRequestDto);


    CreateEventRequest fromDto(CreateEventRequestDto createEventRequestDto);


    CreateEventResponseDto toDto(Event event);
}

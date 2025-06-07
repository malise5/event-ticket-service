package com.malise5.tickets.mappers;

import com.malise5.tickets.domain.CreateEventRequest;
import com.malise5.tickets.domain.CreateTicketTypeRequest;
import com.malise5.tickets.dtos.CreateEventRequestDto;
import com.malise5.tickets.dtos.CreateEventResponseDto;
import com.malise5.tickets.dtos.CreateTicketTypeRequestDto;
import com.malise5.tickets.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.UUID;

@Mapper(componentModel = "spring", 
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UUID.class})
public interface EventMapper {

    CreateTicketTypeRequest fromDto(CreateTicketTypeRequestDto createTicketTypeRequestDto);
    CreateEventRequest fromDto(CreateEventRequestDto createEventRequestDto);
    
    @Mapping(target = "id", source = "id", qualifiedByName = "toUUID")
    CreateEventResponseDto toDto(Event event);
    
    @Named("toUUID")
    default UUID toUUID(Long id) {
        return id != null ? UUID.nameUUIDFromBytes(id.toString().getBytes()) : null;
    }
}

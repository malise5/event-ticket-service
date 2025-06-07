package com.malise5.tickets.dtos;

import com.malise5.tickets.enums.EventStatusEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventRequestDto {

    @NotBlank(message = "Event Name is required")
    private String name;

    @NotBlank(message = "Event Venue is required")
    private String venue;

    private LocalDateTime start;

    private LocalDateTime end;

    private LocalDateTime salesStart;

    private LocalDateTime salesEnd;

    @NotBlank(message = "Event Status is required")
    private EventStatusEnum status;

    @NotBlank(message = "Ticket Types are required")
    @Valid
    private List<CreateTicketTypeRequestDto> ticketTypes;
}

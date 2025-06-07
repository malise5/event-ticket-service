package com.malise5.tickets.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketTypeRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Price is required")
    @PositiveOrZero(message = "Price must be greater than or equal to zero")
    private Double price;
    private String description;
    private Integer totalAvailable;
}

package com.example.aviatickets.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateTicketDTO {
    private String strArrTime;
    private String strDepTime;
    private String description;
    private String destination;
    private String departure;
    private String transportType;
    @Min(1)
    private long price;
    @Min(1)
    private int amount;
}

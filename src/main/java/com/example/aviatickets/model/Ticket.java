package com.example.aviatickets.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {
  @Id
  @Column(name = "ticket_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ticketId;
  @NotBlank
  private String destination;
  @NotBlank
  private String departure;
  @NotBlank
  private String description;
  @NotNull
  private LocalDateTime departureTime;
  @NotNull
  private LocalDateTime arrivalTime;
  @NotBlank
  private String strDepTime;
  @NotBlank
  private String strArrTime;
  @NotBlank
  private String transportType;
  @Min(1)
  private Long price;
  @Min(0)
  private Integer amount;
  @OneToMany(mappedBy = "ticket")
  private List<Order> orders;
}

package com.example.aviatickets.service;


import com.example.aviatickets.exception.NotFound;
import com.example.aviatickets.repositories.TicketRepo;
import com.example.aviatickets.model.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TicketService {
  private final TicketRepo ticketRepo;

  public List<Ticket> getTickets() {
    return ticketRepo.findAll();
  }

  public List<Ticket> getTicketsAvailable(){
    return ticketRepo.findAllAvailable();
  }

  public void saveTicket(Ticket ticket) {
    ticketRepo.save(ticket);
  }

  public Ticket findById(Long ticketId) {
    return ticketRepo.findById(ticketId).orElseThrow(() -> new NotFound("Error getting ticket"));
  }

  public List<Ticket> findByFilters(String keywordDep, String keywordDest,
                                    String keywordDepTime, String keywordTransport) {
    if (keywordDep != null || keywordDest != null || keywordDepTime != null || keywordTransport != null) {
      return ticketRepo.findByFilters(keywordDep, keywordDest,
              keywordDepTime, keywordTransport);
    }
    return ticketRepo.findAllAvailable();
  }

  public List<Ticket> getAllItemsSorted(String sortBy, String direction) {
    Sort sort = direction.equalsIgnoreCase("asc") ?
            Sort.by(sortBy).ascending() :
            Sort.by(sortBy).descending();

    return ticketRepo.findAll(sort);
  }
}

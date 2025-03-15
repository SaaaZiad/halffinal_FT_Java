package com.example.aviatickets;

import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.repositories.TicketRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class ArticleLiveTest extends BaseIT {

    @Autowired
    public TicketRepo ticketRepo;

    @Test
    void givenAnArticle_whenPersisted_thenShouldBeAbleToReadIt() {
        Ticket ticket = new Ticket();
        ticket.setDestination("Санкт-Петербург");
        ticket.setPrice(5500L);

        ticketRepo.save(ticket);

        Ticket persisted = ticketRepo.findAll().getFirst();
        assertThat(persisted.getTicketId()).isNotNull();
        assertThat(persisted.getDestination()).isEqualTo("Санкт-Петербург");
        assertThat(persisted.getPrice()).isEqualTo(5500);
    }
}
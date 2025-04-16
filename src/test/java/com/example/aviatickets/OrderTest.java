package com.example.aviatickets;

import com.example.aviatickets.model.Order;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.repositories.OrderRepo;
import com.example.aviatickets.repositories.TicketRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class OrderTest extends BaseIT {

    @Autowired
    public OrderRepo orderRepo;

    @Test
    void givenAnArticle_whenPersisted_thenShouldBeAbleToReadIt() {
        Order order = new Order();
        order.setAmount(15);
        order.setTicket(new Ticket());

        orderRepo.save(order);

        Order persisted = orderRepo.findAll().getFirst();
        assertThat(persisted.getOrderId()).isNotNull();
        assertThat(persisted.getAmount()).isEqualTo(15);
    }
}
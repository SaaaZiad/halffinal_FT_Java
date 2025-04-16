package com.example.aviatickets;

import com.example.aviatickets.model.Order;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.model.User;
import com.example.aviatickets.repositories.OrderRepo;
import com.example.aviatickets.repositories.TicketRepo;
import com.example.aviatickets.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@AllArgsConstructor
public class UserTest extends BaseIT {

    @Autowired
    public UserRepo userRepo;

    @Test
    void givenAnArticle_whenPersisted_thenShouldBeAbleToReadIt() {
        User user = new User();
        user.setUserName("abc");
        user.setPassword("123123");

        userRepo.save(user);

        User persisted = userRepo.findAll().getFirst();
        assertThat(persisted.getUserName()).isEqualTo("abc");
        assertThat(persisted.getPassword()).isEqualTo("123123");
    }
}
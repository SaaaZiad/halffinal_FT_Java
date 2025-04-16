package com.example.aviatickets.facade;

import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.OrderService;
import com.example.aviatickets.service.TicketService;
import com.example.aviatickets.service.UserService;
import com.example.aviatickets.model.Order;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@AllArgsConstructor
@Component
public class OrderFacade {
  private final TicketService ticketService;
  private final OrderService orderService;
  private final UserService userService;

  public void getOrdersPage(Model model){
    var orders = orderService.findByUserId(AuthenticationUtils.getUserId());
    model.addAttribute("orders", orders);
  }

  public void getDenyPage(Model model, Long orderId) {
    model.addAttribute("ticketId", orderId);
  }

  public void cancelBooking(Long orderId) {
    Order order = orderService.findById(orderId);
    Ticket ticket = order.getTicket();
    User user = order.getUser();
    user.setBalance(user.getBalance() + order.getAmount() * ticket.getPrice());
    ticket.setAmount(ticket.getAmount() + order.getAmount());
    userService.saveUser(user);
    ticketService.saveTicket(ticket);
    orderService.deleteOrder(orderId);
  }
}

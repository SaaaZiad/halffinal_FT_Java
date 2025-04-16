package com.example.aviatickets.facade;


import com.example.aviatickets.dto.CreateTicketDTO;
import com.example.aviatickets.security.AuthenticationUtils;
import com.example.aviatickets.service.TicketService;
import com.example.aviatickets.service.UserService;
import com.example.aviatickets.dto.ConfirmAdminDTO;
import com.example.aviatickets.model.Ticket;
import com.example.aviatickets.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@Component
@PropertySource("classpath:application.yml")
public class AdminFacade {
  private final TicketService ticketService;
  private final UserService userService;
  private final String secret = "adminpassword";

  public void getConfirmPage(Model model) {
    model.addAttribute("confirmAdminDTO", new ConfirmAdminDTO());
  }

  public void confirmAdmin(String confirmKey, Model model) {
    Long userId = AuthenticationUtils.getUserId();
    if (confirmKey.equals(secret)) {
      var user = userService.findUserById(userId);
      user.setRole(Role.ADMIN);
      userService.saveUser(user);
      AuthenticationUtils.updateRole(Role.ADMIN);
    } else {
      model.addAttribute("errorConfirming", "Wrong confirm code");
    }
  }

  public void getTicketsPage(Model model){
    model.addAttribute("tickets", ticketService.getTickets());
  }

  public void getCreateTicketPage(Model model) {
    model.addAttribute("createTicketDTO", new CreateTicketDTO());;
  }

  public void createTicket(CreateTicketDTO dto) throws Exception{
    Ticket ticket = new Ticket();
    ticket.setAmount(dto.getAmount());
    ticket.setPrice(dto.getPrice());
    ticket.setDescription(dto.getDescription());
    ticket.setDeparture(dto.getDeparture());
    ticket.setDestination(dto.getDestination());
    ticket.setStrArrTime(dto.getStrArrTime());
    ticket.setStrDepTime(dto.getStrDepTime());
    ticket.setTransportType(dto.getTransportType());
    ticket.setDepartureTime(LocalDateTime.parse(dto.getStrDepTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    ticket.setArrivalTime(LocalDateTime.parse(dto.getStrArrTime(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
    ticketService.saveTicket(ticket);
  }
}

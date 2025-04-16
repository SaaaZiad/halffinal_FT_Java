package com.example.aviatickets.controllers;

import com.example.aviatickets.dto.BuyTicketDTO;
import com.example.aviatickets.facade.AvailableTicketsFacade;
import com.example.aviatickets.exception.NotEnoughTickets;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/tickets")
public class AvailableTicketsController {
  private final AvailableTicketsFacade availableTicketsFacade;

  @GetMapping
  public String getProductsPage(Model model,
                                @Param("keywordDep") String keywordDep,
                                @Param("keywordDest") String keywordDest,
                                @Param("keywordDepTime") String keywordDepTime,
                                @Param("keywordDepTime") String keywordTransport,
                                @RequestParam(defaultValue = "ticketId") String sortBy,
                                @RequestParam(defaultValue = "asc") String direction){
    availableTicketsFacade.getProductsPage(model, keywordDep, keywordDest, keywordDepTime, keywordTransport,
            sortBy, direction);
    return "available_tickets";
  }

  @GetMapping("/buy_tickets/{ticketId}")
  public String getBuyPage(@PathVariable("ticketId") Long ticketId, Model model){
    availableTicketsFacade.getBuyPage(model, ticketId);
    return "buy_tickets";
  }

  @PostMapping("/buy_tickets/{ticketId}")
  public String buyTicket(Model model,
                          @PathVariable("ticketId") Long ticketId,
                          @ModelAttribute("buyDTO") BuyTicketDTO dto,
                          BindingResult bindingResult){
    if(bindingResult.hasErrors()){
      return "buy_tickets";
    }
    try{
      availableTicketsFacade.buyProduct(ticketId, dto);
    }catch (NotEnoughTickets e){
      model.addAttribute("error", "Недостаточно билетов для приобретения");
      return "buy_tickets";
    }
    return "redirect:/orders";
  }
}


package com.example.aviatickets.controllers;


import com.example.aviatickets.facade.OrderFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/orders")
public class OrderController {
  private final OrderFacade orderFacade;
  @GetMapping
  public String getOrdersPage(Model model){
    orderFacade.getOrdersPage(model);
    return "orders";
  }
  @GetMapping("/cancel_booking/{orderId}")
  public String getDenyPage(@PathVariable("orderId") Long orderId, Model model){
    orderFacade.getDenyPage(model, orderId);
    return "cancel_booking";
  }
  @PostMapping("/cancel_booking/{orderId}")
  public String cancelBooking(@PathVariable("orderId") Long orderId){
    orderFacade.cancelBooking(orderId);
    return "redirect:/orders";
  }
}

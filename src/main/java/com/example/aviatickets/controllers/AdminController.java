package com.example.aviatickets.controllers;


import com.example.aviatickets.dto.CreateTicketDTO;
import com.example.aviatickets.dto.ConfirmAdminDTO;
import com.example.aviatickets.facade.AdminFacade;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {
  private final AdminFacade adminFacade;

  //Создание страницы верификации администратора
  @GetMapping("/confirm")
  public String getConfirmPage(Model model) {
    adminFacade.getConfirmPage(model);
    return "admin/admin_confirm";
  }

  //Обработка страницы ввода данных для верификации
  @PostMapping("/confirm")
  public String confirmAdmin(Model model,
                             @ModelAttribute("confirmAdminDTO") @Valid ConfirmAdminDTO confirmAdminDTO,
                             BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "admin/admin_confirm";
    }
    adminFacade.confirmAdmin(confirmAdminDTO.getConfirmKey(), model);
    if (model.getAttribute("errorConfirming") != null) {
      return "admin/admin_confirm";
    } else {
      return "redirect:/admin/available_tickets";
    }
  }

  //Создание главной страницы после авторизации админа
  @GetMapping
  public String getMainPage() {
    return "admin/available_tickets";
  }

  //Создание страницы списка билетов для админа
  @GetMapping("/available_tickets")
  public String getTicketsPage(Model model) {
    adminFacade.getTicketsPage(model);
    return "admin/admin_available_tickets";
  }

  //Создание страницы создания нового билета
  @GetMapping("/available_tickets/create")
  public String getCreateTicketPage(Model model) {
    adminFacade.getCreateTicketPage(model);
    return "admin/admin_create_ticket";
  }

  //Обработка ввода страницы создания нового билета
  @PostMapping("/available_tickets/create")
  public String createTicket(@ModelAttribute("createTicketDTO") @Valid CreateTicketDTO createTicketDTO,
                             BindingResult bindingResult) throws Exception {
    if (bindingResult.hasErrors()) {
      return "admin/admin_create_ticket";
    }
    adminFacade.createTicket(createTicketDTO);
    return "redirect:/admin/available_tickets";
  }


}
package com.example.aviatickets.service;

import com.example.aviatickets.exception.NotFound;
import com.example.aviatickets.repositories.OrderRepo;
import com.example.aviatickets.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
  private final OrderRepo orderRepo;
  public void saveOrder(Order order){
    orderRepo.save(order);
  }
  public List<Order> findByUserId(Long userId){
    return orderRepo.findOrdersByUserId(userId);
  }
  public Order findById(Long orderId) {
    return orderRepo.findById(orderId).orElseThrow(() -> new NotFound("Error getting order"));
  }
  public void deleteOrder(Long orderId) { orderRepo.deleteById(orderId);}
}

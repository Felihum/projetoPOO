package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.order.OrderRequestDTO;
import com.unicarioca.projeto_poo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/findByMonth/{month}/{year}")
    public ResponseEntity<List<Order>> getOrdersByMonth(@PathVariable int month, @PathVariable int year){
        return ResponseEntity.ok(orderService.getOrdersByMonth(month, year));
    }

    @PostMapping("/")
    private ResponseEntity<Order> createOrder(OrderRequestDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @PutMapping("/{idOrder}")
    private ResponseEntity<Order> updateOrder(@PathVariable UUID idOrder, @RequestParam String orderStatus){
        return ResponseEntity.ok(orderService.updateOrder(idOrder, orderStatus));
    }
}

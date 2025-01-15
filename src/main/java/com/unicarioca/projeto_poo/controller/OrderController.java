package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.order.OrderRequestDTO;
import com.unicarioca.projeto_poo.exception.ClientCardDontMatchException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(orderService.getOrderById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/findByMonth/{month}/{year}")
    public ResponseEntity<List<Order>> getOrdersByMonth(@PathVariable int month, @PathVariable int year){
        try{
            return ResponseEntity.ok(orderService.getOrdersByMonth(month, year));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    private ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO orderDTO){
        try{
            return ResponseEntity.ok(orderService.createOrder(orderDTO));
        } catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Product already registered!").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (ClientCardDontMatchException e){
            return ResponseEntity.badRequest().header("message", "The client doesn't have this card!").build();
        }
    }

    @PutMapping("/{idOrder}/{orderStatus}")
    private ResponseEntity<Order> updateOrder(@PathVariable UUID idOrder, @PathVariable String orderStatus){
        try{
            return ResponseEntity.ok(orderService.updateOrder(idOrder, orderStatus));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

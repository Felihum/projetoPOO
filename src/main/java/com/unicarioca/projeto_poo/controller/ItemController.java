package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.item.ItemRequestDTO;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.product.ProductRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/findById/{idItem}")
    public ResponseEntity<Item> getItemById(@PathVariable UUID idItem){
        try{
            return ResponseEntity.ok(itemService.getItemById(idItem));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Item> createItem(@RequestBody ItemRequestDTO itemDTO){
        try{
            return ResponseEntity.ok(itemService.createItem(itemDTO));
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateQuantity/{idItem}")
    public ResponseEntity<Item> updateItemQuantity(@PathVariable UUID idItem, @RequestParam("quantity") Integer quantity){
        try{
            return ResponseEntity.ok(itemService.updateItemQuantity(idItem, quantity));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().header("message", "Item not found!").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/updateOrderOfItem/{idItem}")
    public ResponseEntity<Item> updateItemQuantity(@PathVariable UUID idItem, @RequestParam("idOrder") UUID idOrder){
        try{
            return ResponseEntity.ok(itemService.updateOrderOfItem(idItem, idOrder));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().header("message", "Item not found!").build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "Order not found!").build();
        }
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Void> deleteItem(@PathVariable UUID idItem){
        try{
            itemService.deleteItem(idItem);
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any client registered!").build();
        }
    }
}

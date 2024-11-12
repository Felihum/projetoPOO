package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/findById/{idCard}")
    public ResponseEntity<Card> getCardById(@PathVariable UUID idCard){
        try{
            return ResponseEntity.ok(cardService.getCardById(idCard));
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Card> createCard(@RequestBody CardRequestDTO cardDTO){
        try{
            return ResponseEntity.ok(cardService.createCard(cardDTO));
        }catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Card already registered").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{idCard}")
    public ResponseEntity<Card> updateCard(@PathVariable UUID idCard, @RequestBody CardEditRequestDTO cardDTO){
        try{
            return ResponseEntity.ok(cardService.updateCard(idCard, cardDTO));
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Card already registered").build();
        }
    }

    @DeleteMapping("/{idCard}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID idCard){
        try {
            cardService.deleteCard(idCard);
            return ResponseEntity.noContent().build();
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any card registered!").build();
        }
    }
}

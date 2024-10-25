package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/findById/{idCard}")
    public ResponseEntity<Card> getCardById(@PathVariable UUID idCard){
        return ResponseEntity.ok(cardService.getCardById(idCard));
    }

    @PostMapping("/")
    public ResponseEntity<Card> createCard(@RequestBody CardRequestDTO cardDTO){
        return ResponseEntity.ok(cardService.createCard(cardDTO));
    }

    @PutMapping("{idCard}")
    public ResponseEntity<Card> updateCard(@PathVariable UUID idCard, @RequestBody CardEditRequestDTO cardDTO){
        return ResponseEntity.ok(cardService.updateCard(idCard, cardDTO));
    }

    @DeleteMapping("{idCard}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID idCard){
        cardService.deleteCard(idCard);
        return ResponseEntity.noContent().build();
    }
}

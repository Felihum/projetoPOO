package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientService clientService;

    public Card getCardById(UUID id){
        return cardRepository.findById(id).get();
    }

    public Card createCard(CardRequestDTO cardDTO){
        Card card = new Card();
        Client client = clientService.getClientById(cardDTO.id_client());

        if (verifyExistingCard(cardDTO.card_number())){
            throw new ExistingElementException();
        }

        card.setCard_number(cardDTO.card_number());
        card.setCard_holder(cardDTO.card_holder());
        card.setCvv(cardDTO.cvv());
        card.setValidity_month(cardDTO.validity_month());
        card.setValidity_year(cardDTO.validity_year());
        card.setClient(client);

        cardRepository.save(card);

        return card;
    }

    public Card updateCard(UUID idCard, CardEditRequestDTO cardDTO){
        Card card = cardRepository.findById(idCard).get();

        if (verifyExistingCard(cardDTO.card_number())){
            throw new ExistingElementException();
        }

        card.setCard_number(cardDTO.card_number());
        card.setCard_holder(cardDTO.card_holder());
        card.setCvv(cardDTO.cvv());
        card.setValidity_month(cardDTO.validity_month());
        card.setValidity_year(cardDTO.validity_year());

        cardRepository.saveAndFlush(card);

        return card;
    }

    public void deleteCard(UUID id) {
        if(cardRepository.existsById(id)){
            cardRepository.deleteById(id);
        } else {
            throw new ElementNotFoundException();
        }
    }

    public boolean verifyExistingCard(String card_number) {
        Card card = cardRepository.findCardByCardNumber(card_number);

        return card != null;
    }
}

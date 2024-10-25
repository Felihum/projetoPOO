package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.domain.client.Client;
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

        card.setCard_number(cardDTO.card_number());
        card.setCard_holder(cardDTO.card_holder());
        card.setCvv(cardDTO.cvv());
        card.setValidity_month(cardDTO.validity_month());
        card.setValidity_year(cardDTO.validity_year());

        cardRepository.saveAndFlush(card);

        return card;
    }

    public void deleteCard(UUID id){
        cardRepository.deleteById(id);
    }
}

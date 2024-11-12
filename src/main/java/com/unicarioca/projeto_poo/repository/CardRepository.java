package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CardRepository extends JpaRepository<Card, UUID> {

    @Query("SELECT c FROM Card c WHERE c.card_number LIKE %:card_number%")
    Card findCardByCardNumber(@Param("card_number") String card_number);
}

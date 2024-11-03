package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends  JpaRepository<Card, UUID> {
}

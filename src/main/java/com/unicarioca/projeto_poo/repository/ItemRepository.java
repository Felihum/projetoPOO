package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    @Query("SELECT i FROM Item i WHERE i.order.id = :id_order")
    List<Item> findAllItemsByOrderId(@Param("id_order") UUID id_order);
}

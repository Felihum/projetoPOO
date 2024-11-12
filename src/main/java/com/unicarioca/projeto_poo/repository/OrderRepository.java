package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT o FROM Order o WHERE o.status LIKE 'COMPLETED' AND EXTRACT(MONTH FROM o.order_date) = :month AND EXTRACT(YEAR FROM o.order_date) = :year")
    List<Order> findAllOrdersByMonth(@Param("month") int month, @Param("year") int year);
}

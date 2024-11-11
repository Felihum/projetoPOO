package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    Product findProductByName(@Param("name") String name);
}

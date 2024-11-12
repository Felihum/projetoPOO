package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends  JpaRepository<ProductCategory, UUID> {
    @Query("SELECT c FROM ProductCategory c WHERE c.name LIKE %:name%")
    ProductCategory findCategoryByName(@Param("name") String name);
}

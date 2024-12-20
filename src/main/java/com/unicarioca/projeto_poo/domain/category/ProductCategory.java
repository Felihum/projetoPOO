package com.unicarioca.projeto_poo.domain.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicarioca.projeto_poo.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "categories")
public class ProductCategory {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

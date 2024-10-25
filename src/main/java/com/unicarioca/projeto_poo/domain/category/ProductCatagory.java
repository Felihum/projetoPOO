package com.unicarioca.projeto_poo.domain.category;

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
public class ProductCatagory {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}

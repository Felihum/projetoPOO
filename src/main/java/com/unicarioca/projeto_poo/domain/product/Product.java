package com.unicarioca.projeto_poo.domain.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private Float price;
    private Integer discount;
    private Float final_price;
    private Integer storage_quantity;
    private String description;
    private String img_url;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private ProductCategory category;

    public void setDiscount(Integer discount){
        this.discount = discount;
        calculateFinalPrice();
    }

    public void calculateFinalPrice(){
        this.final_price = (this.price - (((float)this.discount/100) * this.price));
    }
}

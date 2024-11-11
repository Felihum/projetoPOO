package com.unicarioca.projeto_poo.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.client.Client;
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
@Table(name = "items")
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue
    private UUID id;

    private Float total_price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    public void setQuantity(Integer quantity){
        this.quantity = quantity;

        this.total_price = product.getFinal_price() * quantity;
    }
}

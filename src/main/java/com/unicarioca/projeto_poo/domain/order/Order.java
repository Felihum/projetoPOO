package com.unicarioca.projeto_poo.domain.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private UUID id;

    private Float price;

    private Integer discount;
    private Float final_price;
    private String description;
    private Date order_date;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "id_card")
    private Card card;

    @OneToMany(mappedBy = "order")
    private List<Item> items;

    public void setDiscount(Integer discount){
        this.discount = discount;
        setFinalPrice();
    }

    public void setFinalPrice(){
        this.final_price = (this.price - (((float)this.discount/100) * this.price));
    }
}

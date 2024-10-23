package com.unicarioca.projeto_poo.domain.card;

import com.unicarioca.projeto_poo.domain.user.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue
    private UUID id;

    private BigInteger card_number;
    private String card_holder;
    private Integer cvv;
    private Date validity_date;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

}

package com.unicarioca.projeto_poo.domain.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unicarioca.projeto_poo.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    private UUID id;

    private String street;
    private Integer number;
    private String complement;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}

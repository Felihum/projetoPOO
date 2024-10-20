package com.unicarioca.projeto_poo.domain.address;

import com.unicarioca.projeto_poo.domain.user.User;
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
    private String complement;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
}

package com.unicarioca.projeto_poo.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user_tb")
@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String role;
}

package com.unicarioca.projeto_poo.repository;

import com.unicarioca.projeto_poo.domain.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {
    @Query("SELECT c FROM Client c WHERE c.email LIKE %:email%")
    Client findClientByEmail(@Param("email") String email);
}

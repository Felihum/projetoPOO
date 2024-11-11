package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.client.ClientEditRequestDTO;
import com.unicarioca.projeto_poo.domain.client.ClientRequestDTO;
import com.unicarioca.projeto_poo.exception.client.ClientEmailAlreadyExistsException;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/findById/{idClient}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID idClient){
        try{
            return ResponseEntity.ok(clientService.getClientById(idClient));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<Client> getClientByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(clientService.getClientByEmail(email));
        } catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO clientDTO){
        try{
            return ResponseEntity.ok(clientService.createClient(clientDTO));
        } catch (ClientEmailAlreadyExistsException e){
            return ResponseEntity.badRequest().header("message", "Email already registered").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{idClient}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID idClient, @RequestBody ClientEditRequestDTO userDTO){
        try {
            return ResponseEntity.ok(clientService.updateClient(idClient, userDTO));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ClientEmailAlreadyExistsException e){
            return ResponseEntity.badRequest().header("message", "Email already registered").build();
        }
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID idClient){
        try{
            clientService.deleteClient(idClient);
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any client registered!").build();
        }
    }
}

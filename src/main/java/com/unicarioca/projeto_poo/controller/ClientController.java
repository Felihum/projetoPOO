package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.user.Client;
import com.unicarioca.projeto_poo.domain.user.ClientRequestDTO;
import com.unicarioca.projeto_poo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/findById/{idClient}")
    public ResponseEntity<Client> getClientById(@PathVariable UUID idClient){
        return ResponseEntity.ok(clientService.getClientById(idClient));
    }

    @PostMapping("/")
    public ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO clientDTO){
        Client client = clientService.createClient(clientDTO);

        return client != null ?
                    ResponseEntity.ok(client)
                :
                    ResponseEntity.badRequest().header("message", "Não foi possível cadastrar o usuário. Email já existente.").build();

    }

    @PutMapping("/{idClient}")
    public ResponseEntity<Client> updateClient(@PathVariable UUID idClient, @RequestBody ClientRequestDTO userDTO){
        return ResponseEntity.ok(clientService.updateClient(idClient, userDTO));
    }

    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID idClient){
        clientService.deleteClient(idClient);
        return ResponseEntity.noContent().build();
    }
}

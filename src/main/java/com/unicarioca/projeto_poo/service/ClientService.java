package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.user.Client;
import com.unicarioca.projeto_poo.domain.user.ClientCategory;
import com.unicarioca.projeto_poo.domain.user.ClientRequestDTO;
import com.unicarioca.projeto_poo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client getClientById(UUID id){
        return clientRepository.findById(id).get();
    }

    public Client createClient(ClientRequestDTO clientDTO){
        Client client = new Client();

//        if(verifyExistingEmail(clientDTO.email())){
//
//        }
        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPassword(clientDTO.password());
        client.setRole(ClientCategory.valueOf(clientDTO.role().toUpperCase()));

        clientRepository.save(client);

        return client;
    }

    public Client updateClient(UUID idClient, ClientRequestDTO clientDTO){
        Client client = clientRepository.findById(idClient).get();

        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPassword(clientDTO.password());
        client.setRole(ClientCategory.valueOf(clientDTO.role().toUpperCase()));

        clientRepository.saveAndFlush(client);

        return client;
    }

    public void deleteClient(UUID id){
        clientRepository.deleteById(id);
    }

//    private boolean verifyExistingEmail(String email){
//        Client client = clientRepository.findClientByEmail(email);
//
//        return client != null;
//    }


}

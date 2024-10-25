package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.client.ClientCategory;
import com.unicarioca.projeto_poo.domain.client.ClientEditRequestDTO;
import com.unicarioca.projeto_poo.domain.client.ClientRequestDTO;
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
    public Client getClientByEmail(String email){
        return clientRepository.findClientByEmail(email);
    }

    public Client createClient(ClientRequestDTO clientDTO){
        Client client = new Client();

        if(!verifyExistingEmail(clientDTO.email()) && verifyClientData(clientDTO)){
            client.setName(clientDTO.name());
            client.setEmail(clientDTO.email());
            client.setPassword(clientDTO.password());
            client.setRole(ClientCategory.valueOf(clientDTO.role().toUpperCase()));

            clientRepository.save(client);

            return client;
        }

        return null;
    }

    public Client updateClient(UUID idClient, ClientEditRequestDTO clientDTO){
        Client client = clientRepository.findById(idClient).get();

        client.setName(clientDTO.name());
        client.setEmail(clientDTO.email());
        client.setPassword(clientDTO.password());

        clientRepository.saveAndFlush(client);

        return client;
    }

    public void deleteClient(UUID id){
        clientRepository.deleteById(id);
    }

    private Boolean verifyExistingEmail(String email){
        Client client = clientRepository.findClientByEmail(email);

        return client != null;
    }

    private Boolean verifyClientData(ClientRequestDTO clientDTO){
        return clientDTO.name() != null && clientDTO.email() != null && clientDTO.password() != null;
    }


}

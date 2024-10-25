package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.address.AddressRequestDTO;
import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ClientService clientService;

    public Address getAddressById(UUID id){
        return addressRepository.findById(id).get();
    }

    public Address createAddress(AddressRequestDTO addressDTO){
        Address address = new Address();
        Client client = clientService.getClientById(addressDTO.id_client());

        address.setStreet(addressDTO.street());
        address.setComplement(addressDTO.complement());
        address.setClient(client);

        addressRepository.save(address);

        return address;
    }

    public Address updateAddress(UUID idAddress, AddressRequestDTO addressDTO){
        Address address = addressRepository.findById(idAddress).get();

        address.setStreet(addressDTO.street());
        address.setComplement(addressDTO.complement());

        addressRepository.saveAndFlush(address);

        return address;
    }

    public void deleteAddress(UUID id){
        addressRepository.deleteById(id);
    }

}

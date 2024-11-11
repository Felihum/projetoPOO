package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.address.AddressRequestDTO;
import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.service.AddressService;
import com.unicarioca.projeto_poo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/findById/{idAddress}")
    public ResponseEntity<Address> getCardById(@PathVariable UUID idAddress){
        return ResponseEntity.ok(addressService.getAddressById(idAddress));
    }

    @PostMapping("/")
    public ResponseEntity<Address> createCard(@RequestBody AddressRequestDTO addressDTO){
        return ResponseEntity.ok(addressService.createAddress(addressDTO));
    }

    @PutMapping("/{idAddress}")
    public ResponseEntity<Address> updateCard(@PathVariable UUID idAddress, @RequestBody AddressRequestDTO addressDTO){
        return ResponseEntity.ok(addressService.updateAddress(idAddress, addressDTO));
    }

    @DeleteMapping("/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID idAddress){
        addressService.deleteAddress(idAddress);
        return ResponseEntity.noContent().build();
    }
}

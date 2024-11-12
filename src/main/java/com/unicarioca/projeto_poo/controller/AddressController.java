package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.address.AddressRequestDTO;
import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.card.CardEditRequestDTO;
import com.unicarioca.projeto_poo.domain.card.CardRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.AddressService;
import com.unicarioca.projeto_poo.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/findById/{idAddress}")
    public ResponseEntity<Address> getAddressdById(@PathVariable UUID idAddress){
        try{
            return ResponseEntity.ok(addressService.getAddressById(idAddress));
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Address> createAddress(@RequestBody AddressRequestDTO addressDTO){
        try{
            return ResponseEntity.ok(addressService.createAddress(addressDTO));
        }catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Address already registered").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{idAddress}")
    public ResponseEntity<Address> updateAddress(@PathVariable UUID idAddress, @RequestBody AddressRequestDTO addressDTO){
        try{
            return ResponseEntity.ok(addressService.updateAddress(idAddress, addressDTO));
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Address already registered").build();
        }
    }

    @DeleteMapping("/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable UUID idAddress){
        try{
            addressService.deleteAddress(idAddress);
            return ResponseEntity.noContent().build();
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any address registered!").build();
        }
    }
}

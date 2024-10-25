package com.unicarioca.projeto_poo.domain.address;

import java.util.UUID;

public record AddressRequestDTO(String street, Integer number, String complement, UUID id_client) {
}

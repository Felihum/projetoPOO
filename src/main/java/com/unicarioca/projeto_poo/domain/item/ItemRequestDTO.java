package com.unicarioca.projeto_poo.domain.item;

import java.util.UUID;

public record ItemRequestDTO(Integer quantity, UUID idUser, UUID idProduct) {
}

package com.unicarioca.projeto_poo.domain.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequestDTO(String name, Float price, Integer discount, UUID id_category, Integer storage_quantity, String description, String img_url) {
}

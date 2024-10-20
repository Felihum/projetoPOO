package com.unicarioca.projeto_poo.domain.product;

import java.math.BigDecimal;

public record ProductRequestDTO(String name, BigDecimal price, BigDecimal discount, String category, Integer storage_quantity, String description, String img_url) {
}

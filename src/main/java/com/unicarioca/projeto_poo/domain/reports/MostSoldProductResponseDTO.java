package com.unicarioca.projeto_poo.domain.reports;

import com.unicarioca.projeto_poo.domain.product.Product;

public record MostSoldProductResponseDTO(Product product, Integer quantity) {
}

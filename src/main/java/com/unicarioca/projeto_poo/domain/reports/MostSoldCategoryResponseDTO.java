package com.unicarioca.projeto_poo.domain.reports;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;

public record MostSoldCategoryResponseDTO(ProductCategory category, Integer quantity) {
}

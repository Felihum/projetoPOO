package com.unicarioca.projeto_poo.domain.card;

import java.math.BigInteger;

public record CardEditRequestDTO(String card_number, String card_holder, String cvv, Integer validity_month, Integer validity_year) {
}

package com.unicarioca.projeto_poo.domain.card;

import java.math.BigInteger;

public record CardEditRequestDTO(BigInteger card_number, String card_holder, Integer cvv, Integer validity_month, Integer validity_year) {
}

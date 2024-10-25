package com.unicarioca.projeto_poo.domain.card;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

public record CardRequestDTO(String card_number, String card_holder, String cvv, Integer validity_month, Integer validity_year, UUID id_client){
}

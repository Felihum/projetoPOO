package com.unicarioca.projeto_poo.domain.card;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

public record CardRequestDTO(BigInteger card_number, String card_holder, Integer cvv, Date validity_date, UUID id_user){
}

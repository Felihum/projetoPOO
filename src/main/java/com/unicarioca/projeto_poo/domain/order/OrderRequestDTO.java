package com.unicarioca.projeto_poo.domain.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public record OrderRequestDTO(Float price, Integer discount, String description, Date order_date, String status, UUID id_client, UUID id_address, UUID id_card) {
}

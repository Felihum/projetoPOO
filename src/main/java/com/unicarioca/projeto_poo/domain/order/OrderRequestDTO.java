package com.unicarioca.projeto_poo.domain.order;

import com.unicarioca.projeto_poo.domain.item.Item;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(List<UUID> id_items, Integer discount, String description, UUID id_client, UUID id_address, UUID id_card) {
}

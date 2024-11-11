package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.item.ItemRequestDTO;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    public Item getItemById(UUID idItem){
        return itemRepository.findById(idItem).get();
    }

    public Item createItem(ItemRequestDTO itemDTO){
        Item item = new Item();

        Client client = clientService.getClientById(itemDTO.id_client());
        Product product = productService.getProductById(itemDTO.id_product());

        item.setQuantity(itemDTO.quantity());
        item.setClient(client);
        item.setProduct(product);

        itemRepository.save(item);

        return item;
    }

    public Item updateOrderOfItem(UUID idItem, UUID idOrder){
        Item item = itemRepository.findById(idItem).get();

        Order order = orderService.getOrderById(idOrder);

        if(order == null){
            throw new ElementNotFoundException();
        }

        item.setOrder(order);

        itemRepository.saveAndFlush(item);

        return item;
    }

    public Item updateItemQuantity(UUID idItem, Integer quantity){
        Item item = itemRepository.findById(idItem).get();

        item.setQuantity(quantity);

        itemRepository.saveAndFlush(item);

        return item;
    }

    public void deleteItem(UUID idItem){
        if(itemRepository.existsById(idItem)){
            itemRepository.deleteById(idItem);
        } else {
            throw new ElementNotFoundException();
        }
    }
}

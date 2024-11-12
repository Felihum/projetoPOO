package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.item.ItemRequestDTO;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.repository.ItemRepository;
import com.unicarioca.projeto_poo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private OrderRepository orderRepository;

    public Item getItemById(UUID idItem){
        return itemRepository.findById(idItem).get();
    }

    public List<Item> getAllItemsByOrderId(UUID idOrder){
        return itemRepository.findAllItemsByOrderId(idOrder);
    }

    public Item createItem(ItemRequestDTO itemDTO){
        Item item = new Item();

        Client client = clientService.getClientById(itemDTO.id_client());
        Product product = productService.getProductById(itemDTO.id_product());

        item.setProduct(product);
        item.setQuantity(itemDTO.quantity());
        item.setClient(client);

        itemRepository.save(item);

        return item;
    }

    public Item updateOrderOfItem(UUID idItem, UUID idOrder){

        if(!orderRepository.existsById(idOrder)){
            throw new ElementNotFoundException();
        }

        Item item = itemRepository.findById(idItem).get();

        Order order = orderRepository.findById(idOrder).get();

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

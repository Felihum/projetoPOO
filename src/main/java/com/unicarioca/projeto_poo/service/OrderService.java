package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.order.OrderRequestDTO;
import com.unicarioca.projeto_poo.domain.order.OrderStatus;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.repository.ItemRepository;
import com.unicarioca.projeto_poo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CardService cardService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    public Order getOrderById(UUID id){
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrdersByMonth(int month, int year){
        List<Order> orders = orderRepository.findAllOrdersByMonth(month, year);

        if(!orders.isEmpty()){
            return orders;
        } else{
            throw new ElementNotFoundException();
        }
    }

    public Order createOrder(OrderRequestDTO orderDTO){
        Order order = new Order();
        Client client = clientService.getClientById(orderDTO.id_client());
        Address address = addressService.getAddressById(orderDTO.id_address());
        Card card = cardService.getCardById(orderDTO.id_card());

        List<Item> items = new ArrayList<>();

        for (UUID id : orderDTO.id_items()){
            items.add(itemService.getItemById(id));
        }

        Float price = 0.0f;

        for(Item item : items){
            price += item.getTotal_price();
        }

        order.setPrice(price);
        order.setDiscount(orderDTO.discount());
        order.setDescription(orderDTO.description());
        order.setOrder_date(new Date());
        order.setStatus(OrderStatus.valueOf("PENDING"));
        order.setClient(client);
        order.setAddress(address);
        order.setCard(card);

        orderRepository.save(order);

        for(Item item : items){
            itemService.updateOrderOfItem(item.getId(), order.getId());
        }

        return order;
    }

    public Order updateOrder(UUID idOrder, String orderStatus){
        Order order = orderRepository.findById(idOrder).get();

        order.setStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));

        orderRepository.saveAndFlush(order);

        return order;
    }
}

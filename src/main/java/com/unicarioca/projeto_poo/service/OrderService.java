package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.address.Address;
import com.unicarioca.projeto_poo.domain.card.Card;
import com.unicarioca.projeto_poo.domain.client.Client;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.order.OrderRequestDTO;
import com.unicarioca.projeto_poo.domain.order.OrderStatus;
import com.unicarioca.projeto_poo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Order getOrderById(UUID id){
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrdersByMonth(int month, int year){
        return orderRepository.findAllOrdersByMonth(month, year);
    }

    public Order createOrder(OrderRequestDTO orderDTO){
        Order order = new Order();
        Client client = clientService.getClientById(orderDTO.id_client());
        Address address = addressService.getAddressById(orderDTO.id_address());
        Card card = cardService.getCardById(orderDTO.id_card());

        Float final_price = (orderDTO.price() - (((float)orderDTO.discount()/100) * orderDTO.price()));

        order.setPrice(orderDTO.price());
        order.setDiscount(orderDTO.discount());
        order.setFinal_price(final_price);
        order.setDescription(orderDTO.description());
        order.setOrder_date(orderDTO.order_date());
        order.setStatus(OrderStatus.valueOf(orderDTO.status().toUpperCase()));
        order.setClient(client);
        order.setAddress(address);
        order.setCard(card);

        orderRepository.save(order);

        return order;
    }

    public Order updateOrder(UUID idOrder, String orderStatus){
        Order order = orderRepository.findById(idOrder).get();

        order.setStatus(OrderStatus.valueOf(orderStatus.toUpperCase()));

        return order;
    }
}

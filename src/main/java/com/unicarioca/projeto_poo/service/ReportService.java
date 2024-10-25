package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    private OrderService orderService;

    public Product getMostSaledProductOfMonth(int month, int year){
        List<Product> products = getAllSaledProductsPerMonth(month, year);
        Collections.sort(products, Comparator.comparing(p -> p.getName()));
        int qntMajor = 0;
        int qntAux = 0;
        Product major = new Product();
        Product productAux;
        for(int i = 0; i < products.size(); i++){
            productAux = products.get(i);
            qntAux++;
            if(major == null){
                major = productAux;
                qntMajor++;
            } else{
                if(productAux == major){
                    qntMajor++;
                } else{
                    if(productAux == products.get(i-1)){
                        if(qntAux > qntMajor){
                            major = productAux;
                            qntMajor = qntAux;
                        }
                    } else{
                        qntAux = 1;
                    }
                }
            }
        }

        return major;
    }

    public List<Product> getAllSaledProductsPerMonth(int month, int year){
        List<Order> orders = orderService.getOrdersByMonth(month, year);
        List<Product> productsOfMonth = new ArrayList<>();
        for(Order order : orders){
            for(Item item : order.getItems()){
                for(int i = 0; i < item.getQuantity(); i++){
                    productsOfMonth.add(item.getProduct());
                }
            }
        }

        return productsOfMonth;
    }
}

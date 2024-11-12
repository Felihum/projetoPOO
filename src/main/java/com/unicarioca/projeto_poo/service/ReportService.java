package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.item.Item;
import com.unicarioca.projeto_poo.domain.order.Order;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.reports.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReportService {

    @Autowired
    private OrderService orderService;

    public MostSoldProductResponseDTO getMostSoldProductOfMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        int qntMajor = 0;
        int qntAux = 0;
        Product major = null;
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

        return new MostSoldProductResponseDTO(major, qntMajor);
    }

    public List<Product> getAllSoldProductsPerMonth(int month, int year){
        List<Order> orders = orderService.getOrdersByMonth(month, year);
        List<Product> productsOfMonth = new ArrayList<Product>();
        for(Order order : orders){
            for(Item item : order.getItems()){
                for(int i = 0; i < item.getQuantity(); i++){
                    productsOfMonth.add(item.getProduct());
                }
            }
        }

        Collections.sort(productsOfMonth, Comparator.comparing(p -> p.getName()));

        return productsOfMonth;
    }

    public List<ProductCategory> getAllCategoriesSoldPerMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            if(categories.isEmpty()){
                categories.add(product.getCategory());
            } else{
                if(!categories.contains(product.getCategory())){
                    categories.add(product.getCategory());
                }
            }
        }

        return categories;
    }

    public List<ProductCategory> getAllCategoriesSoldPerMonthWithoutFilter(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            categories.add(product.getCategory());
        }

        return categories;
    }

    public MostSoldCategoryResponseDTO getMostSoldCategoryOfMonth(int month, int year){
        List<Product> products = getAllSoldProductsPerMonth(month, year);

        List<ProductCategory> categories = new ArrayList<ProductCategory>();

        for(Product product : products){
            categories.add(product.getCategory());
        }

        Collections.sort(categories, Comparator.comparing(c -> c.getName()));

        int qntMajor = 0;
        int qntAux = 0;
        ProductCategory major = null;
        ProductCategory categoryAux;
        for(int i = 0; i < categories.size(); i++){
            categoryAux = categories.get(i);
            qntAux++;
            if(major == null){
                major = categoryAux;
                qntMajor++;
            } else{
                if(categoryAux == major){
                    qntMajor++;
                } else{
                    if(categoryAux == categories.get(i-1)){
                        if(qntAux > qntMajor){
                            major = categoryAux;
                            qntMajor = qntAux;
                        }
                    } else{
                        qntAux = 1;
                    }
                }
            }
        }

        return new MostSoldCategoryResponseDTO(major, qntMajor);
    }

    public Integer getNumberOfSalesPerCategory(ProductCategory category, int month, int year){
        List<ProductCategory> categories = getAllCategoriesSoldPerMonthWithoutFilter(month, year);
        int numSales = 0;

        for (ProductCategory productCategory : categories){
            if(productCategory.equals(category)){
                numSales++;
            }
        }

        return numSales;
    }

    public Float getTotalAmountCollectedInMonth(int month, int year){
        List<Order> orders = orderService.getOrdersByMonth(month, year);

        Iterator<Order> ordersIterator = orders.listIterator();

        Float amount = 0.0f;

        while (ordersIterator.hasNext()){
            amount += ordersIterator.next().getFinal_price();
        }

        return amount;
    }

    private Object getMostElementInList(List list){
        int qntMajor = 0;
        int qntAux = 0;
        Object major = null;
        Object objAux;
        for(int i = 0; i < list.size(); i++){
            objAux = list.get(i);
            qntAux++;
            if(major == null){
                major = objAux;
                qntMajor++;
            } else{
                if(objAux == major){
                    qntMajor++;
                } else{
                    if(objAux == list.get(i-1)){
                        if(qntAux > qntMajor){
                            major = objAux;
                            qntMajor = qntAux;
                        }
                    } else{
                        qntAux = 1;
                    }
                }
            }
        }

        return new MostElementInListResponseDTO(major, qntMajor);
    }
}

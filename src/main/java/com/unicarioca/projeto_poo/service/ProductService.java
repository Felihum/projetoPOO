package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.client.ClientEditRequestDTO;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.product.ProductRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    public Product getProductById(UUID id){
        return productRepository.findById(id).get();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductByName(String name){
        Product product = productRepository.findProductByName(name);

        if(product != null){
            return product;
        } else{
            throw new ElementNotFoundException();
        }
    }

    public Product createProduct(ProductRequestDTO productDTO){
        if(verifyExistingProduct(productDTO.name())){
            throw new ExistingElementException();
        }

        Product product = new Product();

        ProductCategory category = categoryService.getCategoryById(productDTO.id_category());

        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDiscount(productDTO.discount());
        product.setStorage_quantity(productDTO.storage_quantity());
        product.setCategory(category);
        product.setDescription(productDTO.description());
        product.setImg_url(productDTO.img_url());

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(UUID idProduct, ProductRequestDTO productDTO){

        if(verifyExistingProduct(productDTO.name())){
            throw new ExistingElementException();
        }

        Product product = productRepository.findById(idProduct).get();

        ProductCategory category = categoryService.getCategoryById(productDTO.id_category());

        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setDiscount(productDTO.discount());
        product.setStorage_quantity(productDTO.storage_quantity());
        product.setCategory(category);
        product.setDescription(productDTO.description());
        product.setImg_url(productDTO.img_url());

        productRepository.saveAndFlush(product);

        return product;
    }

    public Product updateProductStorageQuantity(UUID idProduct, Integer storage_quantity){
        Product product = productRepository.findById(idProduct).get();

        product.setStorage_quantity(storage_quantity);

        productRepository.saveAndFlush(product);

        return product;
    }

    public void deleteProduct(UUID idProduct){
        if(productRepository.existsById(idProduct)){
            productRepository.deleteById(idProduct);
        } else {
            throw new ElementNotFoundException();
        }
    }

    public boolean verifyExistingProduct(String name){
        Product product = productRepository.findProductByName(name);

        return product != null;
    }
}

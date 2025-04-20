package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.product.ProductRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/findById/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable UUID idProduct){
        try{
            return ResponseEntity.ok(productService.getProductById(idProduct));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            return ResponseEntity.ok(productService.getAllProducts());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequestDTO productDTO){
        try {
            return ResponseEntity.ok(productService.createProduct(productDTO));
        } catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Product already registered!").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID idProduct, @RequestBody ProductRequestDTO productDTO){
        try{
            return ResponseEntity.ok(productService.updateProduct(idProduct, productDTO));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
//        catch (ExistingElementException e){
//            System.out.println(e);
//            return ResponseEntity.badRequest().header("message", "Product already registered!").build();
//        }
    }

    @PutMapping("/updateStorageQuantity/{idProduct}")
    public ResponseEntity<Product> updateProductStorageQuantity(@PathVariable UUID idProduct, @RequestParam(value = "storage_quantity") Integer storage_quantity){
        try{
            return ResponseEntity.ok(productService.updateProductStorageQuantity(idProduct, storage_quantity));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID idProduct){
        try{
            productService.deleteProduct(idProduct);
            return ResponseEntity.noContent().build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any client registered!").build();
        }
    }
}

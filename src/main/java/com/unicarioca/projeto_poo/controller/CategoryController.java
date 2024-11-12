package com.unicarioca.projeto_poo.controller;



import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.category.ProductCategoryRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findByID/{idCategory}")
    public ResponseEntity<ProductCategory> getCategoryById(@PathVariable UUID idCategory)
    {
        try{
            return ResponseEntity.ok(categoryService.getCategoryById(idCategory));
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductCategory>> getAllCategories()
    {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping("/")
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategoryRequestDTO categoryDTO)
    {
        try {
            return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
        }catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Category already registered").build();
        } catch (DataAccessException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

        }
    }


    @PutMapping("/{idCategory}")
    public ResponseEntity<ProductCategory> updateCategory(@PathVariable UUID idCategory, @RequestBody ProductCategoryRequestDTO categoryDTO){
        try{
            return ResponseEntity.ok(categoryService.updateCategory(idCategory, categoryDTO));
        }catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ExistingElementException e){
            return ResponseEntity.badRequest().header("message", "Category already registered").build();
        }
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID idCategory){
        try{
            categoryService.deleteCategory(idCategory);
            return ResponseEntity.noContent().build();
        }catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "The id don't match with any category registered!").build();
        }
    }


}


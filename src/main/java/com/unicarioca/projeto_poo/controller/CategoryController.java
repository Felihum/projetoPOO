package com.unicarioca.projeto_poo.controller;



import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.category.ProductCategoryRequestDTO;
import com.unicarioca.projeto_poo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/findByID/{idCategory}")
    public ResponseEntity<ProductCategory> getCardByid(@PathVariable UUID idCategory)
    {
        return ResponseEntity.ok(categoryService.getCategoryById(idCategory));
    }

    @PostMapping("/")
    public ResponseEntity<ProductCategory> createCard(@RequestBody ProductCategoryRequestDTO categoryDTO)
    {
        return ResponseEntity.ok(categoryService.createCategory(categoryDTO));
    }


    @PutMapping("{idCategory}")
    public ResponseEntity<ProductCategory> updateCard(@PathVariable UUID idCategory, @RequestBody ProductCategoryRequestDTO categoryDTO){
        return ResponseEntity.ok(categoryService.updateCategory(idCategory, categoryDTO));
    }

    @DeleteMapping("{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID idCategory){
        categoryService.deleteCategory(idCategory);
        return ResponseEntity.noContent().build();
    }


}


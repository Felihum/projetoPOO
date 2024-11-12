package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.category.ProductCategoryRequestDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.exception.ExistingElementException;
import com.unicarioca.projeto_poo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductCategory getCategoryById(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public List<ProductCategory> getAllCategories(){
        return categoryRepository.findAll();
    }

    public ProductCategory createCategory(ProductCategoryRequestDTO categoryDTO)
    {
        ProductCategory category = new ProductCategory();
        if (verifyExistingCategory(categoryDTO.name())){
            throw new ExistingElementException();
        }

        category.setName(categoryDTO.name());

        categoryRepository.save(category);

        return category;
    }


    public ProductCategory updateCategory(UUID idCategory , ProductCategoryRequestDTO categoryDTO)
    {
        ProductCategory category = categoryRepository.findById(idCategory).get();
        if (verifyExistingCategory(categoryDTO.name())){
            throw new ExistingElementException();
        }

        category.setName(categoryDTO.name());

        categoryRepository.saveAndFlush(category);

        return category;
    }

    public void deleteCategory(UUID id) {
        if(categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
        } else {
            throw new ElementNotFoundException();
        }
    }

    public boolean verifyExistingCategory(String name) {
        ProductCategory category = categoryRepository.findCategoryByName(name);

        return category != null;
    }
}

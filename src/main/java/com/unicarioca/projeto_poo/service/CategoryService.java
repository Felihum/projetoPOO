package com.unicarioca.projeto_poo.service;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.category.ProductCategoryRequestDTO;
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

    public ProductCategory createCategory(ProductCategoryRequestDTO CategoryDTO)
    {
        ProductCategory category = new ProductCategory();

        category.setName(CategoryDTO.name());

        categoryRepository.save(category);

        return category;
    }


    public ProductCategory updateCategory(UUID idCategory , ProductCategoryRequestDTO categoryDTO)
    {
        ProductCategory category = categoryRepository.findById(idCategory).get();

        category.setName(categoryDTO.name());

        categoryRepository.saveAndFlush(category);

        return category;
    }

    public void deleteCategory(UUID id) {categoryRepository.deleteById((id));}

}

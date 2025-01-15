package com.unicarioca.projeto_poo.controller;

import com.unicarioca.projeto_poo.domain.category.ProductCategory;
import com.unicarioca.projeto_poo.domain.product.Product;
import com.unicarioca.projeto_poo.domain.reports.MostSoldCategoryResponseDTO;
import com.unicarioca.projeto_poo.domain.reports.MostSoldProductResponseDTO;
import com.unicarioca.projeto_poo.exception.ElementNotFoundException;
import com.unicarioca.projeto_poo.service.CategoryService;
import com.unicarioca.projeto_poo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Controller
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/mostSoldProductOfMonth")
    public ResponseEntity<MostSoldProductResponseDTO> getMostSoldProductOfMonth(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getMostSoldProductOfMonth(month, year));
    }

    @GetMapping("/allSoldProductsPerMonth")
    public ResponseEntity<List<Product>> getAllSoldProductsPerMonth(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getAllSoldProductsPerMonth(month, year));
    }

    @GetMapping("/allCategoriesSoldPerMonth")
    public ResponseEntity<List<ProductCategory>> getAllCategoriesSoldPerMonth(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getAllCategoriesSoldPerMonth(month, year));
    }

    @GetMapping("/allCategoriesSoldPerMonthWithoutFilter")
    public ResponseEntity<List<ProductCategory>> getAllCategoriesSoldPerMonthWithoutFilter(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getAllCategoriesSoldPerMonthWithoutFilter(month, year));
    }

    @GetMapping("/mostSoldCategoryOfMonth")
    public ResponseEntity<MostSoldCategoryResponseDTO> getMostSoldCategoryOfMonth(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getMostSoldCategoryOfMonth(month, year));
    }

    @GetMapping("/numberOfSalesPerCategory")
    public ResponseEntity<Integer> getNumberOfSalesPerCategory(@RequestParam UUID idCategory, @RequestParam int month, @RequestParam int year){
        try{
            ProductCategory category = categoryService.getCategoryById(idCategory);

            return ResponseEntity.ok(reportService.getNumberOfSalesPerCategory(category, month, year));
        } catch (ElementNotFoundException e){
            return ResponseEntity.notFound().header("message", "Category not found!").build();
        }
    }

    @GetMapping("/totalAmountCollectedInMonth")
    public ResponseEntity<Float> getTotalAmountCollectedInMonth(@RequestParam int month, @RequestParam int year){
        return ResponseEntity.ok(reportService.getTotalAmountCollectedInMonth(month, year));
    }
}

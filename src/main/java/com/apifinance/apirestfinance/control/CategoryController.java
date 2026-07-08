package com.apifinance.apirestfinance.control;

import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.model.TransactionType;
import com.apifinance.apirestfinance.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody @Valid Category category) {
        return categoryService.createCategory(category);
    }

    @GetMapping("/categorias/listacategorias")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/categorias/categoria-id")
    public Optional<Category> getCategoryById(@RequestParam @Valid long id) {
        return categoryService.getCategoryById(id);
    }
}

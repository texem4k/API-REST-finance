package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.model.TransactionType;
import com.apifinance.apirestfinance.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public Category createCategory(Category category) {
        if(category.getName().length()>4 || category.getName().matches(".*\\d.*")){
            throw new CategoryNotValid("Categoría no válida");
        }
        return categoryRepository.save(category);
    }


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(long id) {
        return categoryRepository.findById(id);
    }
}

package com.apifinance.apirestfinance.control;


import com.apifinance.apirestfinance.model.Categorization;
import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.service.CategorizationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorizacion")
public class CategorizationController {

    private final CategorizationService categorizationService;

    public CategorizationController(CategorizationService categorizationService) {
        this.categorizationService = categorizationService;
    }

    @PostMapping
    public Categorization createRelation(@RequestBody @Valid Categorization categorization) {
        return categorizationService.createRelation(categorization);
    }

    @GetMapping("/categorizacion/total")
    public List<Categorization> findAll() {
        return categorizationService.getAllCategorizations();
    }

}

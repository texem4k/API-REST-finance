package com.apifinance.apirestfinance.service;


import com.apifinance.apirestfinance.model.Categorization;
import com.apifinance.apirestfinance.repositories.CategorizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorizationService {

    private final CategorizationRepository categorizationRepository;


    public CategorizationService(CategorizationRepository categorizationRepository) {
        this.categorizationRepository = categorizationRepository;
    }
    public Categorization createRelation(Categorization categorization) {
        return categorizationRepository.save(categorization);
    }

    public List<Categorization> getAllCategorizations() {
        return categorizationRepository.findAll();
    }

    public Categorization getCategorizationById(Long id) {
        return categorizationRepository.findById(id).orElse(null);
    }

    public Categorization getCategorizationByKeyWord(String keyWord) {
        return categorizationRepository.findByKeyWord(keyWord);
    }
}

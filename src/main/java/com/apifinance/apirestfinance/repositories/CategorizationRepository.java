package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.Categorization;
import com.apifinance.apirestfinance.model.Category;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorizationRepository extends JpaRepository<Categorization, Long> {

    //La propia interfaz tiene métodos como:
    //save(Entidad)
    //findById(Id)
    //findAll()
    //deleteById(Id)
    //count()
}

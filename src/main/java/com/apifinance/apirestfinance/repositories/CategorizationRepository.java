package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.Categorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorizationRepository extends JpaRepository<Categorization, Long> {
    Categorization findByKeyWord(String keyWord);

    //La propia interfaz tiene métodos como:
    //save(Entidad)
    //findById(Id)
    //findAll()
    //deleteById(Id)
    //count()
}

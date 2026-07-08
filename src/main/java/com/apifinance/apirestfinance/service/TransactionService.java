package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.model.*;
import com.apifinance.apirestfinance.repositories.CategorizationRepository;
import com.apifinance.apirestfinance.repositories.CategoryRepository;
import com.apifinance.apirestfinance.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Clase que realiza operaciones con transacciones
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategorizationRepository reglaRepository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository transactionRepository,
                              CategorizationRepository reglaRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.reglaRepository = reglaRepository;
        this.categoryRepository = categoryRepository;
    }

    /**
     * Crea una transacción y se comunica con el repository para conectarse a la DB
     * @param transaction Una transacción
     * @return La misma transacción
     */
    public Transaction create(Transaction transaction) {
        valuateCoherenceCategory(transaction);
        return transactionRepository.save(transaction);
    }

    /**
     * Asigna una categoría de forma automática a una descripción o nombre de transacción
     */
    private Category categorizeAuto(String description) {
        String normalizedDescription = description.toLowerCase();

        return (Category) reglaRepository.findAll().stream()
                .filter(rule -> normalizedDescription.contains(rule.getKeyWord().toLowerCase()))
                .map(Categorization::getCategory)
                .findFirst()
                .orElseGet(() -> categoryRepository.findByName("Sin clasificar")
                        .orElseThrow(() -> new IllegalStateException(
                                "No existe la categoría 'Sin clasificar' — créala primero en la BD")));
    }

    /**
     * Valida la coherencia de la categoría
     */
    private void valuateCoherenceCategory(Transaction t) {
        if (t.getType() != t.getCategory().getType()) {
            throw new CategoryAssigmentError(
                    "No se puede asignar una categoría de " + t.getCategory().getType() +
                            " a una transacción de tipo " + t.getType());
        }
    }


    /**
     * Devuelve una lista de transacciones dada una fecha inicial y final
     */
    public List<Transaction> getHistorical(User user, LocalDate since, Optional<LocalDate> until) {
        return transactionRepository.findByOwnerAndDateBetween(user, since, until.orElse(LocalDate.now()));
    }


    // --- Balance total ---
    public BigDecimal calculateBalance(User user) {
        List<Transaction> transactions = transactionRepository.findByOwner(user);

        return transactions.stream()
                .map(t -> t.getType() == TransactionType.INCOME
                        ? t.getAmount()
                        : t.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal allPerCategory(User user, long categoryId) {
        return transactionRepository.findByOwnerAndCategoryId(user, categoryId).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

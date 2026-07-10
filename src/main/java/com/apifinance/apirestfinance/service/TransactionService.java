package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.CategoryAssigmentError;
import com.apifinance.apirestfinance.control.exceptions.TransactionDetailsError;
import com.apifinance.apirestfinance.model.*;
import com.apifinance.apirestfinance.repositories.CategorizationRepository;
import com.apifinance.apirestfinance.repositories.CategoryRepository;
import com.apifinance.apirestfinance.repositories.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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


    public Transaction create(Transaction transaction) {
        if(verifyTransactionDetails(transaction)){
            throw new TransactionDetailsError("Datos de la transacción no válidos");
        }

        Transaction newTransaction = setCategory(transaction);
        return transactionRepository.save(newTransaction);
    }

    private boolean verifyTransactionDetails(Transaction transaction) {
        return transaction.getName().isEmpty() || transaction.getId().toString().isEmpty();
    }

    private Transaction setCategory(Transaction transaction) {
        Category category = categorizeAuto(transaction.getDescription());
        transaction.setCategory(category);
        analyzeCategory(transaction);
        return transaction;
    }

    private void analyzeCategory(Transaction transaction) throws CategoryAssigmentError {
        valuateCoherenceCategory(transaction);
    }

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

    private void valuateCoherenceCategory(Transaction t) throws CategoryAssigmentError {
        if (t.getType() != t.getCategory().getType()) {
            throw new CategoryAssigmentError(
                    "No se puede asignar una categoría de " + t.getCategory().getType() +
                            " a una transacción de tipo " + t.getType());
        }
    }



    public Page<Transaction> getHistorical(User user, LocalDate since, Optional<LocalDate> until, Pageable pageable) {
        return transactionRepository.findByOwnerAndDateBetween(user, since, until.orElse(LocalDate.now()), pageable);
    }


    public BigDecimal calculateBalance(User user) {
        Pageable pageable = PageRequest.of(0, 20);
        Page<Transaction> transactions = transactionRepository.findByOwner(user, pageable);

        return transactions.stream()
                .map(t -> t.getType() == TransactionType.INCOME
                        ? t.getAmount()
                        : t.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public BigDecimal allPerCategory(User user, long categoryId) {
        Pageable pageable = PageRequest.of(0, 20);
        return transactionRepository.findByOwnerAndCategoryId(user, categoryId, pageable).stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Transaction findById(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Page<Transaction> findAllTransactions(Pageable p){
        return transactionRepository.findAll(p);
    }
}

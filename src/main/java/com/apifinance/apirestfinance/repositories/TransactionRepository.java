package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findByOwnerAndDateBetween(User user, LocalDate desde, LocalDate hasta, Pageable pageable);

    Page<Transaction> findByOwnerAndCategoryId(User user, long categoryId, Pageable pageable);

    Page<Transaction> findByOwner(User user, Pageable pageable);

    Transaction findById(User user, Pageable pageable);

    Transaction save(Transaction t);
}

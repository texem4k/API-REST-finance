package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByOwnerAndDateBetween(User user, LocalDate desde, LocalDate hasta);

    List<Transaction> findByOwnerAndCategoryId(User user, long categoryId);

    List<Transaction> findByOwner(User user);

    Transaction save(Transaction t);
}

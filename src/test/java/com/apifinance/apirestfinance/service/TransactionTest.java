package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {

    private final Pageable pageable = PageRequest.of(0, 10);
    private final TransactionService transactionService;
    private final UserService userService;
    public TransactionTest(TransactionService transactionService, UserService userService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @Test
    public void findTransactionByIdTest(){
        Transaction firstTrans = transactionService.findAllTransactions(pageable).stream().findFirst().orElseThrow();
        assertEquals(firstTrans, transactionService.findById(firstTrans.getId()));
    }


    @Test
    public void createTransactionWithCorrectInformationTest(){
        Transaction transaction = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.getAllUsers(pageable).stream().findFirst().get(),"");
        Transaction result = transactionService.create(transaction);
        assertEquals(result, transaction);
    }

    //Falta por añadir más tests

}

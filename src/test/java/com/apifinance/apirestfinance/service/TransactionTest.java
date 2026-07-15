package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.TransactionDetailsError;
import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.TransactionType;
import com.apifinance.apirestfinance.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransactionTest {

    private final Pageable pageable = PageRequest.of(0, 10);
    private final TransactionService transactionService;
    private final UserService userService;
    private final CategorizationService categorizationService;

    public TransactionTest(TransactionService transactionService, UserService userService, CategorizationService categorizationService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.categorizationService = categorizationService;
    }


    @Test
    public void findTransactionByIdTest(){
        Transaction firstTrans = transactionService.findAllTransactions(pageable).stream().findFirst().orElseThrow();
        assertEquals(firstTrans, transactionService.findById(firstTrans.getId()));
    }


    @Test
    public void createTransactionWithCorrectInformationTest(){
        Transaction transaction = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"");
        Transaction result = transactionService.create(transaction);
        assertEquals(result, transaction);
    }


    @Test
    public void createTransactionWithIncorrectInformationTest(){
        Transaction transaction = Transaction.createTransaction("", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"");
        assertThrows(TransactionDetailsError.class, () -> transactionService.create(transaction));
    }


    @Test
    public void setCorrectCategoryToTransactionTest(){
        Transaction transaction1 = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"pizza margarita");
        Transaction transaction2 = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"hola");
        Category cat1 = transactionService.categorizeAuto(transaction1.getDescription());
        Category cat2 = transactionService.categorizeAuto(transaction2.getDescription());
        assertEquals(cat1, categorizationService.getCategorizationByKeyWord("pizza").getCategory());
        assertEquals(cat2, categorizationService.getCategorizationByKeyWord("Sin clasificar").getCategory());
    }


    @Test
    public void calculateBalanceTest(){

        User newUser = User.createUser("Calcular balance user", "balance@gmail.com", "Balance123.");
        userService.createUser(newUser);

        Transaction transaction1 = Transaction.createTransaction("Transacción ejemplo1", BigDecimal.valueOf(50), userService.findUserByEmail(newUser.getEmail()),"ingreso1");
        Transaction transaction2 = Transaction.createTransaction("Transacción ejemplo2", BigDecimal.valueOf(10), userService.findUserByEmail(newUser.getEmail()),"ingreso2");
        Transaction transaction3 = Transaction.createTransaction("Transacción ejemplo3", BigDecimal.valueOf(5), userService.findUserByEmail(newUser.getEmail()),"gasto1");

        transactionService.create(transaction1);
        transactionService.create(transaction2);
        transactionService.create(transaction3);

        BigDecimal balance = transactionService.calculateBalance(newUser);
        assertEquals(BigDecimal.valueOf(55), balance);
    }


    @Test
    public void getHistoricalTest(){

        User user = User.createUser("Calcular balance user", "balance@gmail.com", "Balance123.");;
        assertEquals(transactionService.findByOwner(user,pageable), transactionService.getHistorical(user, LocalDate.of(2026, 7, 10), Optional.empty(), pageable));
    }


}

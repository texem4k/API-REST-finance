package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.TransactionDetailsError;
import com.apifinance.apirestfinance.model.Category;
import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.TransactionType;
import com.apifinance.apirestfinance.model.User;
import com.apifinance.apirestfinance.repositories.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TransactionTest {

    private final Pageable pageable = PageRequest.of(0, 10);

    @Mock
    private TransactionRepository transactionRepository;


    @InjectMocks
    private final TransactionService transactionService;

    @InjectMocks
    private final UserService userService;

    @InjectMocks
    private final CategorizationService categorizationService;

    public TransactionTest(TransactionService transactionService, UserService userService, CategorizationService categorizationService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.categorizationService = categorizationService;
    }


    @Test
    public void createTransactionWithIncorrectInformationTest(){
        Transaction transaction = Transaction.createTransaction("", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"", TransactionType.INCOME);
        assertThrows(TransactionDetailsError.class, () -> transactionService.create(transaction));
        verify(transactionRepository, never()).save(any());
    }


    @Test
    public void setCorrectCategoryToTransactionTest(){
        Transaction transaction1 = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"pizza margarita", TransactionType.EXPENSE);
        Transaction transaction2 = Transaction.createTransaction("Transacción ejemplo", BigDecimal.valueOf(50), userService.findAllUsers(pageable).stream().findFirst().get(),"hola", TransactionType.INCOME);
        Category cat1 = transactionService.categorizeAuto(transaction1.getDescription());
        Category cat2 = transactionService.categorizeAuto(transaction2.getDescription());
        assertEquals(cat1, categorizationService.getCategorizationByKeyWord("pizza").getCategory());
        assertEquals(cat2, categorizationService.getCategorizationByKeyWord("Sin clasificar").getCategory());
    }

    @Test
    public void calculateBalanceTest(){

        User newUser = User.createUser("Calcular balance user", "balance@gmail.com", "Balance123.");

        List<Transaction> list = new ArrayList<>();

        Transaction transaction1 = Transaction.createTransaction("Transacción ejemplo1", BigDecimal.valueOf(50), newUser,"ingreso1", TransactionType.INCOME);
        Transaction transaction2 = Transaction.createTransaction("Transacción ejemplo2", BigDecimal.valueOf(10), newUser,"ingreso2", TransactionType.INCOME);
        Transaction transaction3 = Transaction.createTransaction("Transacción ejemplo3", BigDecimal.valueOf(5), newUser,"gasto1", TransactionType.EXPENSE);

        list.add(transaction1);
        list.add(transaction2);
        list.add(transaction3);


        BigDecimal balance = transactionService.calculateBalanceByTransactions(list);
        assertEquals(BigDecimal.valueOf(55), balance);
    }

    @Test
    public void calculateBalanceByCategories(){
        User newUser = User.createUser("Calcular balance user", "balance@gmail.com", "Balance123.");

        Category cat1 = new Category("Ingreso", TransactionType.INCOME);
        Category cat2 = new Category("Dividendo", TransactionType.INCOME);
        Category cat3 = new Category("Comida", TransactionType.EXPENSE);
        Category cat4 = new Category("Ocio", TransactionType.EXPENSE);
        Transaction transaction1 = Transaction.createTransaction("Transacción ejemplo1", BigDecimal.valueOf(50), newUser,"ingreso", TransactionType.INCOME);
        Transaction transaction2 = Transaction.createTransaction("Transacción ejemplo2", BigDecimal.valueOf(10), newUser,"dividendo", TransactionType.INCOME);
        Transaction transaction3 = Transaction.createTransaction("Transacción ejemplo3", BigDecimal.valueOf(5), newUser,"almuerzo", TransactionType.EXPENSE);

        List<Transaction> t= new ArrayList<>();
        assertEquals(BigDecimal.valueOf(50), transactionService.allPerCategory(t, cat1));
        assertEquals(BigDecimal.valueOf(10), transactionService.allPerCategory(t, cat2));
        assertEquals(BigDecimal.valueOf(0), transactionService.allPerCategory(t, cat3));
        assertEquals(BigDecimal.valueOf(0), transactionService.allPerCategory(t, cat4));
    }
}

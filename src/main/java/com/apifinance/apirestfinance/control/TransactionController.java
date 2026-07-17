package com.apifinance.apirestfinance.control;

import com.apifinance.apirestfinance.control.requests.TransactionRequest;
import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.User;
import com.apifinance.apirestfinance.service.TransactionService;
import com.apifinance.apirestfinance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


/**
 * Clase que recibe peticiones HTTP y usa el TransactionService para realizar operaciones
 */
@RestController
@RequestMapping("/transacciones")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    private Pageable firstPageWithTenElements = PageRequest.of(0, 10);

    public TransactionController(TransactionService transactionService, UserService userService) {
        this.transactionService = transactionService;
        this.userService = userService;
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody @Valid TransactionRequest t) {
        User user = userService.findUserByEmail(t.getOwnerEmail());
        return transactionService.create(Transaction.createTransaction(t.getName(),t.getAmount(),user,t.getDescription()));
    }

    @GetMapping("/historial")
    public Page<Transaction> getHistorical(@RequestParam User user, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @RequestParam(defaultValue ="#{T(java.time.LocalDate).now().toString()}" ) LocalDate to) {
        return transactionService.getHistorical(user, from, Optional.ofNullable(to), firstPageWithTenElements);
    }

    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestBody @Valid User user) {
        return transactionService.calculateBalance(user);
    }

    @GetMapping("/total-categoria")
    public BigDecimal getTotalPerCategory(@RequestBody @Valid User user,@RequestParam long categoryId) {
        return transactionService.allPerCategory(user, categoryId);
    }

    @GetMapping("/ejemplo")
    public String getExample() {
        return "ejemplo";
    }

    public Transaction findById(UUID id) {
        return transactionService.findById(id);
    }


}

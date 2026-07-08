package com.apifinance.apirestfinance.control;

import com.apifinance.apirestfinance.model.Transaction;
import com.apifinance.apirestfinance.model.User;
import com.apifinance.apirestfinance.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


/**
 * Clase que recibe peticiones HTTP y usa el TransactionService para realizar operaciones
 */
@RestController
@RequestMapping("/transacciones")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody @Valid Transaction t) {
        return transactionService.create(t);
    }

    @GetMapping("/historial")
    public List<Transaction> getHistorical(@RequestParam User user, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from, @RequestParam(defaultValue ="#{T(java.time.LocalDate).now().toString()}" ) LocalDate to) {
        return transactionService.getHistorical(user, from, Optional.ofNullable(to));
    }

    @GetMapping("/balance")
    public BigDecimal getBalance(@RequestBody @Valid User user) {
        return transactionService.calculateBalance(user);
    }

    @GetMapping("/total-categoria")
    public BigDecimal getBalance(@RequestBody @Valid User user,@RequestParam long categoryId) {
        return transactionService.allPerCategory(user, categoryId);
    }

    @GetMapping("/ejemplo")
    public String getExample() {
        return "ejemplo";
    }


}

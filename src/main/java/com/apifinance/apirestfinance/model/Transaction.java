package com.apifinance.apirestfinance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


/**
 * Clase que define que es una transacción y que datos contiene
 */

@Entity
@Table(name="TRANSACCIONES")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID", unique = true, nullable = false)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario", nullable = false)
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category category;

    @NotNull(message = "La transacción debe indicar su tipo")
    @Enumerated(EnumType.STRING)
    private TransactionType type;


    @Size(max = 100)
    private String description;

    @NotNull(message = "El importe es obligatorio")
    @Positive(message = "El importe debe ser mayor que cero")
    private BigDecimal amount;

    @NotNull(message = "La fecha es obligatoria")
    @PastOrPresent(message = "La fecha no puede ser futura")
    private LocalDate date = LocalDate.now();

    @NotBlank(message = "EL nombre de la transacción no puede estar vacío")
    @Size(min = 3, max = 30, message = "El nombre de la transacción debe tener entre 3 y 30 caracteres")
    private String name;



    private Transaction(String name, BigDecimal amount, User owner, Category cat, String description){
        this.name = name;
        this.amount = amount;
        this.owner = owner;
        this.category = cat;
        this.description = description;
    }

    public Transaction() {

    }

    public BigDecimal getAmount(){
        return amount;
    }

    public Category getCategory(){
        return category;
    }

    public User getOwner(){
        return owner;
    }

    public TransactionType getType(){
        return type;
    }

    public String getDescription(){
        return description;
    }

    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }

    public void setCategory(Category cat){
        this.category = cat;
    }

    public void setOwner(User user){
        this.owner = user;
    }

    public void setTransactionType(TransactionType type){
        this.type = type;
    }

    public void setDescription(String x){
        this.description = x;
    }

}

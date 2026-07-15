package com.apifinance.apirestfinance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


import java.math.BigDecimal;
import java.time.LocalDate;
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
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category category;

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

    private Transaction(String name, BigDecimal amount, User owner, String description) {
        this.name = name;
        this.amount = amount;
        this.owner = owner;
        this.description = description;
    }

    public Transaction() {
    }

    public static Transaction createTransaction(String name, BigDecimal amount, User owner, String description) {
        return new Transaction(name, amount, owner, description);
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

    public String getDescription(){
        return description;
    }

    public  String getName(){
        return name;
    }

    public UUID getId() {
        return id;
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
    public void setDescription(String x){
        this.description = x;
    }

    public void setName(String x){
        this.name = x;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id.equals(that.id);
    }
}

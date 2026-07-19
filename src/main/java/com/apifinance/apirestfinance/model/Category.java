package com.apifinance.apirestfinance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * Clase que define una categoría, dado un tipo ingreso/gasto con la palabra clave o frase
 */
@Entity
@Table(name = "CATEGORIAS")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Column(unique = false, nullable = false)
    private String name;

    @NotNull(message = "La categoría debe tener un tipo (INGRESO o GASTO)")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Category() {}

    public Category(String name, TransactionType type) {
        this.name = name;
        this.type = type;
    }

    public TransactionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }
}
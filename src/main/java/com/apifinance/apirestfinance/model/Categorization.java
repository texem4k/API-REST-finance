package com.apifinance.apirestfinance.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


/**
 * Clase que relaciona palabras claves como "pizza" a una categoría "Comida".
 *
 */
@Entity
@Table(name = "CATEGORIZACION")
public class Categorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @JoinColumn(name = "palabra_clave", nullable = false)
    private String keyWord;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Category category;

    public Categorization() {}

    public Object getCategory() {
        return category;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
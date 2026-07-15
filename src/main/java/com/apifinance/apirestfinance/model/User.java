package com.apifinance.apirestfinance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="USUARIOS")
public class User {

    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    @Size(min = 3, max = 30, message = "El nombre de usuario debe tener entre 3 y 30 caracteres")
    @JoinColumn(name = "nombre usuario", nullable = false, unique = true)

    private String username;

    @JoinColumn(name = "email", nullable = false, unique = true)
    @NotBlank(message = "El nombre de usuario no puede estar vacío")
    private String email;

    @JoinColumn(name = "contraseña", nullable = false)
    private String passwordHash;

    @Column(nullable = true)
    private final LocalDateTime registerDate = LocalDateTime.now();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="ID", unique = true, nullable = false)
    private UUID id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Transaction> transactions;


    public User() {}

    private User(String username, String email, String passwordHash) {
        this.username = username;
        this.email = email;
        this.passwordHash = passwordHash;
        this.transactions = new ArrayList<>();
    }

    public static User createUser(String username, String email, String passwordHash) {
        return new User(username, email, passwordHash);
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setName(String x) {
        this.username = x;
    }

    public void setEmail(String x) {
        this.email = x;
    }

    public void setPasswordHash(String x) {
        this.passwordHash = x;
    }

    public UUID getId() {
        return id;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}

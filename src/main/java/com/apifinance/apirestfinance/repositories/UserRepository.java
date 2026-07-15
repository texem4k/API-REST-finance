package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findById(UUID id);

    Page<User> findAll(Pageable pageable);

    Optional<User> findByEmail(String email);
}

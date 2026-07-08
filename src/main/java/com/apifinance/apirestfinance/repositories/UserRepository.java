package com.apifinance.apirestfinance.repositories;

import com.apifinance.apirestfinance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findUserById(UUID id);
}

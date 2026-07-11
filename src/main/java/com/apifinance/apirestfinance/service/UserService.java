package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.UserDetailsError;
import com.apifinance.apirestfinance.model.*;
import com.apifinance.apirestfinance.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        user.setPasswordHash(user.passwordEncoder().encode(user.getPasswordHash()));
        if(validateUserDetails(user.getName(), user.getEmail(), user.getPasswordHash())) {
            return userRepository.save(user);
        }
        throw new UserDetailsError("Datos introducidos no validos");

    }


    public Page<User> findAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private boolean validateUserDetails(String name, String email, String password) {
        return Validations.validateName(name) && Validations.validateEmail(email) && Validations.validatePassword(password);
    }

}

package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.UserDetailsError;
import com.apifinance.apirestfinance.model.*;
import com.apifinance.apirestfinance.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(UUID id) {
        return userRepository.findUserById(id);
    }

    private boolean validateUserDetails(String name, String email, String password) {
        return Validations.validateName(name) && Validations.validateEmail(email) && Validations.validatePassword(password);
    }

}

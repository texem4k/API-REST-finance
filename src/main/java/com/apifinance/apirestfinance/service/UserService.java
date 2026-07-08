package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.model.*;
import com.apifinance.apirestfinance.repositories.UserRepository;
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
        if(validateUserDetails(user.getName(), user.getEmail(), user.getPasswordHash())) {
            return userRepository.save(user);
        }
        throw new UserDetailsError("Datos introducidos no validos");

    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findUserById(id);
    }

    private boolean validateUserDetails(String name, String email, String password) {
        return Validations.validateName(name) && Validations.validateEmail(email) && Validations.validatePassword(password);
    }

}

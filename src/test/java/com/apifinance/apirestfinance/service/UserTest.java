package com.apifinance.apirestfinance.service;

import com.apifinance.apirestfinance.control.exceptions.UserDetailsError;
import com.apifinance.apirestfinance.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    UserService userService;

    private final Pageable pageable = PageRequest.of(0, 10);
    public UserTest(UserService userService) {
        this.userService = userService;
    }


    @Test
    public void findAllWithoutUsersCreatedTest(){
        assertEquals(0, userService.findAllUsers(pageable).getTotalElements());
    }

    @Test
    public void findUserByIdTest(){
        User firstUser = userService.findAllUsers(pageable).stream().findFirst().orElseThrow();
        assertEquals(firstUser, userService.findUserById(firstUser.getId()).orElse(null));
    }


    @Test
    public void createUserWithCorrectInformationTest(){
        User user = User.createUser("ejemplo", "email@ejemplo.es", "Contraseña123.");
        User result = userService.createUser(user);
        assertEquals(result, user);
    }

    @Test
    public void createUserWithInvalidUsernameTest(){
        User user = User.createUser("", "email@ejemplo.es", "Contraseña123.");
        Exception exception = assertThrows(UserDetailsError.class, () -> {userService.createUser(user);});
        assertEquals("Datos introducidos no validos", exception.getMessage());
    }

    @Test
    public void createUserWithInvalidEmailTest(){
        User user = User.createUser("ejemplo", "email@ejemplo", "Contraseña123.");
        Exception exception = assertThrows(UserDetailsError.class, () -> {userService.createUser(user);});
        assertEquals("Datos introducidos no validos", exception.getMessage());
    }

    @Test
    public void createUserWithInvalidPasswordTest(){
        User user = User.createUser("ejemplo", "email@ejemplo", "contraseña");
        Exception exception = assertThrows(UserDetailsError.class, () -> {userService.createUser(user);});
        assertEquals("Datos introducidos no validos", exception.getMessage());
    }




}

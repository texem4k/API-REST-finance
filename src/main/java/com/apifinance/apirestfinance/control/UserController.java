package com.apifinance.apirestfinance.control;

import com.apifinance.apirestfinance.model.User;
import com.apifinance.apirestfinance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/userlist")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user-id")
    public User getUserById(UUID id) {
        return userService.getUserById(id);
    }

}

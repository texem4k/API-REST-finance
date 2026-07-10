package com.apifinance.apirestfinance.control;

import com.apifinance.apirestfinance.model.User;
import com.apifinance.apirestfinance.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserService userService;
    private Pageable pageable = PageRequest.of(0, 10);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody @Valid User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/userlist")
    public Page<User> getAllUsers() {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/user-id")
    public User getUserById(UUID id) {
        return userService.getUserById(id);
    }

}

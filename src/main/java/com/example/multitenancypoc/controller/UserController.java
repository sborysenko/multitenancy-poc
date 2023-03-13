package com.example.multitenancypoc.controller;

import com.example.multitenancypoc.model.User;
import com.example.multitenancypoc.multitenancy.model.AuthUser;
import com.example.multitenancypoc.multitenancy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{login}")
    public AuthUser getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @GetMapping("/accounts")
    public List<User> getUserAccounts() {
        return userService.getUserAccounts();

    }
}

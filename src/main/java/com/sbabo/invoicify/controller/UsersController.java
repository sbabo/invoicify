package com.sbabo.invoicify.controller;

import com.sbabo.invoicify.model.Users;
import com.sbabo.invoicify.repository.UsersRepository;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping
    public Users createUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @GetMapping
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return usersRepository.findById(id).orElse(null);
    }
}

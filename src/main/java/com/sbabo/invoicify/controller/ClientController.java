package com.sbabo.invoicify.controller;

import com.sbabo.invoicify.model.Client;
import com.sbabo.invoicify.model.Users;

import com.sbabo.invoicify.repository.ClientRepository;
import com.sbabo.invoicify.repository.UsersRepository;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:3000")
public class ClientController {

    private final ClientRepository clientRepository;
    private final UsersRepository usersRepository;

    public ClientController(ClientRepository clientRepository, UsersRepository usersRepository) {
        this.clientRepository = clientRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping
    public Client createClient(@RequestParam Long userId, @RequestBody Client client) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        client.setUser(user);
        return clientRepository.save(client);
    }

    @GetMapping
    public List<Client> getClientsByUserId(@RequestParam Long userId) {
        return clientRepository.findByUserId(userId);
    }
    
}

package com.fastFood.controllers;

import com.fastFood.clientes.Client;
import com.fastFood.dtos.ClientDTO;
import com.fastFood.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientDTO client) {

        Client newClient = clientService.createClient(client);

        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        
        List<Client> clients = this.clientService.getAllClients();

        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
